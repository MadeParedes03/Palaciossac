package com.project.Palaciossac.service;

import com.project.Palaciossac.customer.infrastructure.adapter.out.CustomerJpaRepository;
import com.project.Palaciossac.customer.infrastructure.entities.CustomerEntity;
import com.project.Palaciossac.dto.SaleDetailResponse;
import com.project.Palaciossac.dto.SaleItemRequest;
import com.project.Palaciossac.dto.SaleRequest;
import com.project.Palaciossac.dto.SaleResponse;
import com.project.Palaciossac.entity.Employee;
import com.project.Palaciossac.entity.Product;
import com.project.Palaciossac.entity.Sale;
import com.project.Palaciossac.entity.SaleDetail;
import com.project.Palaciossac.entity.SaleDetailId;
import com.project.Palaciossac.exception.ResourceNotFoundException;
import com.project.Palaciossac.repository.EmployeeRepository;
import com.project.Palaciossac.repository.ProductRepository;
import com.project.Palaciossac.repository.SaleDetailRepository;
import com.project.Palaciossac.repository.SaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final CustomerJpaRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;

    public SaleService(SaleRepository saleRepository,
                       SaleDetailRepository saleDetailRepository,
                       CustomerJpaRepository customerRepository,
                       EmployeeRepository employeeRepository,
                       ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.saleDetailRepository = saleDetailRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
    }

    /**
     * Registra una venta: valida cliente / empleado / productos, verifica stock,
     * descuenta stock y calcula subtotales y total. Todo en una sola transacción:
     * si algo falla, no se descuenta nada.
     */
    @Transactional
    public SaleResponse create(SaleRequest request) {
        CustomerEntity customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado: " + request.getCustomerId()));
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado: " + request.getEmployeeId()));

        // la PK de detalle_venta es (id_venta, id_producto): un producto no puede repetirse
        Set<Long> seen = new HashSet<>();
        for (SaleItemRequest item : request.getItems()) {
            if (!seen.add(item.getProductId())) {
                throw new IllegalArgumentException("El producto " + item.getProductId()
                        + " está repetido en la venta; agrupa las cantidades en un solo ítem");
            }
        }

        Sale sale = new Sale(customer, employee, request.getPaymentType());
        List<SaleDetail> details = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (SaleItemRequest item : request.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: " + item.getProductId()));

            if (!product.hasStock(item.getQuantity())) {
                throw new IllegalArgumentException("Stock insuficiente para: " + product.getName());
            }

            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);

            SaleDetail detail = new SaleDetail(product, item.getQuantity(), product.getSalePrice());
            details.add(detail);
            total = total.add(detail.getSubtotal());
        }

        sale.setTotal(total);

        // 1) guardar la venta para obtener id_venta
        Sale saved = saleRepository.save(sale);

        // 2) completar la PK compuesta de cada detalle y guardarlos
        for (SaleDetail detail : details) {
            detail.setSale(saved);
            detail.setId(new SaleDetailId(saved.getId(), detail.getProduct().getId()));
        }
        saved.getDetails().addAll(saleDetailRepository.saveAll(details));

        return toResponse(saved);
    }

    public List<SaleResponse> findAll() {
        return saleRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public SaleResponse findById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada: " + id));
        return toResponse(sale);
    }

    public List<SaleResponse> findByCustomerDocument(String document) {
        return saleRepository.findByCustomerDocument(document).stream()
                .map(this::toResponse)
                .toList();
    }

    public List<SaleResponse> findByDateRange(LocalDateTime from, LocalDateTime to) {
        return saleRepository.findBySaleDateBetween(from, to).stream()
                .map(this::toResponse)
                .toList();
    }

    public List<SaleResponse> findByProductType(String type) {
        return saleRepository.findSalesByProductType(type).stream()
                .map(this::toResponse)
                .toList();
    }

    private SaleResponse toResponse(Sale sale) {
        List<SaleDetailResponse> items = sale.getDetails().stream()
                .map(d -> new SaleDetailResponse(
                        d.getProduct().getId(),
                        d.getProduct().getName(),
                        d.getQuantity(),
                        d.getUnitPrice(),
                        d.getSubtotal()))
                .toList();

        return new SaleResponse(sale.getId(), sale.getSaleDate(), sale.getCustomer().getName(),
                sale.getEmployee().getName(), sale.getPaymentType(), items, sale.getTotal());
    }
}
