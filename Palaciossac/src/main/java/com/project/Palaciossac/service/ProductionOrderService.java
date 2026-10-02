package com.project.Palaciossac.service;

import com.project.Palaciossac.dto.ProductionOrderRequest;
import com.project.Palaciossac.dto.ProductionOrderResponse;
import com.project.Palaciossac.entity.Employee;
import com.project.Palaciossac.entity.Product;
import com.project.Palaciossac.entity.ProductRecipe;
import com.project.Palaciossac.entity.ProductionOrder;
import com.project.Palaciossac.entity.ProductionStatus;
import com.project.Palaciossac.entity.RawMaterial;
import com.project.Palaciossac.exception.ResourceNotFoundException;
import com.project.Palaciossac.repository.EmployeeRepository;
import com.project.Palaciossac.repository.ProductRecipeRepository;
import com.project.Palaciossac.repository.ProductRepository;
import com.project.Palaciossac.repository.ProductionOrderRepository;
import com.project.Palaciossac.repository.RawMaterialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductionOrderService {

    private final ProductionOrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRecipeRepository recipeRepository;
    private final RawMaterialRepository rawMaterialRepository;

    public ProductionOrderService(ProductionOrderRepository orderRepository,
                                  ProductRepository productRepository,
                                  EmployeeRepository employeeRepository,
                                  ProductRecipeRepository recipeRepository,
                                  RawMaterialRepository rawMaterialRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.employeeRepository = employeeRepository;
        this.recipeRepository = recipeRepository;
        this.rawMaterialRepository = rawMaterialRepository;
    }

    @Transactional
    public ProductionOrderResponse create(ProductionOrderRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: " + request.getProductId()));
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado: " + request.getEmployeeId()));

        ProductionOrder order = new ProductionOrder(product, employee, request.getStartDate(), request.getQuantity());
        return toResponse(orderRepository.save(order));
    }

    public List<ProductionOrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductionOrderResponse findById(Long id) {
        return toResponse(getEntity(id));
    }

    public List<ProductionOrderResponse> findByStatus(ProductionStatus status) {
        return orderRepository.findByStatus(status).stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ProductionOrderResponse> findByEmployee(Long employeeId) {
        return orderRepository.findByEmployeeId(employeeId).stream()
                .map(this::toResponse)
                .toList();
    }

    // Órdenes que todavía no terminan (PENDIENTE y EN_PROCESO)
    public List<ProductionOrderResponse> findActive() {
        return orderRepository.findByStatuses(List.of(ProductionStatus.PENDIENTE, ProductionStatus.EN_PROCESO))
                .stream()
                .map(this::toResponse)
                .toList();
    }

    /** PENDIENTE -> EN_PROCESO */
    @Transactional
    public ProductionOrderResponse start(Long id) {
        ProductionOrder order = getEntity(id);
        if (order.getStatus() != ProductionStatus.PENDIENTE) {
            throw new IllegalArgumentException(
                    "Solo se puede iniciar una orden PENDIENTE (estado actual: " + order.getStatus() + ")");
        }
        order.setStatus(ProductionStatus.EN_PROCESO);
        return toResponse(orderRepository.save(order));
    }

    /**
     * EN_PROCESO -> FINALIZADA.
     * Consume de la materia prima lo que indica la receta (cantidad_requerida x cantidad de la orden)
     * y suma lo fabricado al stock del producto. Si falta algún insumo no se toca nada.
     */
    @Transactional
    public ProductionOrderResponse complete(Long id) {
        ProductionOrder order = getEntity(id);
        if (order.getStatus() != ProductionStatus.EN_PROCESO) {
            throw new IllegalArgumentException(
                    "Solo se puede finalizar una orden EN_PROCESO (estado actual: " + order.getStatus() + ")");
        }

        List<ProductRecipe> recipe = recipeRepository.findByProductId(order.getProduct().getId());
        if (recipe.isEmpty()) {
            throw new IllegalArgumentException(
                    "El producto '" + order.getProduct().getName() + "' no tiene receta de confección");
        }

        BigDecimal units = BigDecimal.valueOf(order.getQuantity());

        // 1) validar que alcance todo el stock antes de descontar
        for (ProductRecipe line : recipe) {
            BigDecimal needed = line.getQuantityRequired().multiply(units);
            if (!line.getRawMaterial().hasStock(needed)) {
                throw new IllegalArgumentException("Stock insuficiente de insumo: " + line.getRawMaterial().getName()
                        + " (necesario " + needed + " " + line.getRawMaterial().getUnitOfMeasure() + ")");
            }
        }

        // 2) descontar insumos
        for (ProductRecipe line : recipe) {
            RawMaterial material = line.getRawMaterial();
            material.setStock(material.getStock().subtract(line.getQuantityRequired().multiply(units)));
            rawMaterialRepository.save(material);
        }

        // 3) sumar producto terminado
        Product product = order.getProduct();
        product.setStock(product.getStock() + order.getQuantity());
        productRepository.save(product);

        order.setStatus(ProductionStatus.FINALIZADA);
        order.setEndDate(LocalDateTime.now());
        return toResponse(orderRepository.save(order));
    }

    /** PENDIENTE / EN_PROCESO -> CANCELADA */
    @Transactional
    public ProductionOrderResponse cancel(Long id) {
        ProductionOrder order = getEntity(id);
        if (order.getStatus() == ProductionStatus.FINALIZADA || order.getStatus() == ProductionStatus.CANCELADA) {
            throw new IllegalArgumentException(
                    "No se puede cancelar una orden " + order.getStatus());
        }
        order.setStatus(ProductionStatus.CANCELADA);
        order.setEndDate(LocalDateTime.now());
        return toResponse(orderRepository.save(order));
    }

    private ProductionOrder getEntity(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden de producción no encontrada: " + id));
    }

    private ProductionOrderResponse toResponse(ProductionOrder o) {
        return new ProductionOrderResponse(
                o.getId(),
                o.getProduct().getId(), o.getProduct().getName(),
                o.getEmployee().getId(), o.getEmployee().getName(),
                o.getStartDate(), o.getEndDate(), o.getQuantity(), o.getStatus());
    }
}
