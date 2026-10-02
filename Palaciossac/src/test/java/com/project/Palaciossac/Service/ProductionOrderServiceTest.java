package com.project.Palaciossac.service;

import com.project.Palaciossac.dto.ProductionOrderRequest;
import com.project.Palaciossac.dto.ProductionOrderResponse;
import com.project.Palaciossac.entity.Employee;
import com.project.Palaciossac.entity.Product;
import com.project.Palaciossac.entity.ProductRecipe;
import com.project.Palaciossac.entity.ProductionOrder;
import com.project.Palaciossac.entity.ProductionStatus;
import com.project.Palaciossac.entity.RawMaterial;
import com.project.Palaciossac.repository.EmployeeRepository;
import com.project.Palaciossac.repository.ProductRecipeRepository;
import com.project.Palaciossac.repository.ProductRepository;
import com.project.Palaciossac.repository.ProductionOrderRepository;
import com.project.Palaciossac.repository.RawMaterialRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductionOrderServiceTest {

    @Mock
    ProductionOrderRepository orderRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    ProductRecipeRepository recipeRepository;
    @Mock
    RawMaterialRepository rawMaterialRepository;

    @InjectMocks
    ProductionOrderService service;

    private Product polo() {
        Product product = new Product("Polo básico", "Polo", "M", "Blanco", new BigDecimal("35.00"), 0);
        product.setId(1L);
        return product;
    }

    private ProductionOrder orderInStatus(Product product, int quantity, ProductionStatus status) {
        ProductionOrder order = new ProductionOrder(product, new Employee("Mario", "Operario", null), null, quantity);
        order.setId(7L);
        order.setStatus(status);
        return order;
    }

    @Test
    void shouldCreateOrderAsPending() {
        Product product = polo();
        Employee employee = new Employee("Mario", "Operario", null);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(employeeRepository.findById(2L)).thenReturn(Optional.of(employee));
        when(orderRepository.save(any(ProductionOrder.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProductionOrderRequest request = new ProductionOrderRequest();
        request.setProductId(1L);
        request.setEmployeeId(2L);
        request.setQuantity(10);

        ProductionOrderResponse response = service.create(request);

        assertEquals(ProductionStatus.PENDIENTE, response.getStatus());
        assertNotNull(response.getStartDate());
        assertNull(response.getEndDate());
        assertEquals(10, response.getQuantity());
    }

    @Test
    void shouldStartOnlyPendingOrders() {
        ProductionOrder finished = orderInStatus(polo(), 5, ProductionStatus.FINALIZADA);
        when(orderRepository.findById(7L)).thenReturn(Optional.of(finished));

        assertThrows(IllegalArgumentException.class, () -> service.start(7L));

        verify(orderRepository, never()).save(any(ProductionOrder.class));
    }

    @Test
    void shouldCompleteOrderConsumingRawMaterialAndIncreasingProductStock() {
        // Arrange: 10 polos -> 12 m de tela (1.2 x 10) y 0.5 conos de hilo (0.05 x 10)
        Product product = polo();
        RawMaterial tela = new RawMaterial("Tela algodón", "metro", new BigDecimal("100.000"));
        tela.setId(1L);
        RawMaterial hilo = new RawMaterial("Hilo industrial", "cono", new BigDecimal("50.000"));
        hilo.setId(2L);
        ProductionOrder order = orderInStatus(product, 10, ProductionStatus.EN_PROCESO);

        when(orderRepository.findById(7L)).thenReturn(Optional.of(order));
        when(recipeRepository.findByProductId(1L)).thenReturn(List.of(
                new ProductRecipe(product, tela, new BigDecimal("1.200")),
                new ProductRecipe(product, hilo, new BigDecimal("0.050"))));
        when(orderRepository.save(any(ProductionOrder.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        ProductionOrderResponse response = service.complete(7L);

        // Assert
        assertEquals(ProductionStatus.FINALIZADA, response.getStatus());
        assertNotNull(response.getEndDate());
        assertEquals(0, new BigDecimal("88.000").compareTo(tela.getStock()));
        assertEquals(0, new BigDecimal("49.500").compareTo(hilo.getStock()));
        assertEquals(10, product.getStock());
        verify(rawMaterialRepository).save(tela);
        verify(rawMaterialRepository).save(hilo);
        verify(productRepository).save(product);
    }

    @Test
    void shouldNotTouchStockWhenRawMaterialIsInsufficient() {
        Product product = polo();
        RawMaterial tela = new RawMaterial("Tela algodón", "metro", new BigDecimal("5.000"));
        tela.setId(1L);
        ProductionOrder order = orderInStatus(product, 10, ProductionStatus.EN_PROCESO);

        when(orderRepository.findById(7L)).thenReturn(Optional.of(order));
        when(recipeRepository.findByProductId(1L)).thenReturn(List.of(
                new ProductRecipe(product, tela, new BigDecimal("1.200"))));

        assertThrows(IllegalArgumentException.class, () -> service.complete(7L));

        assertEquals(0, new BigDecimal("5.000").compareTo(tela.getStock()));
        assertEquals(0, product.getStock());
        assertEquals(ProductionStatus.EN_PROCESO, order.getStatus());
        verify(rawMaterialRepository, never()).save(any(RawMaterial.class));
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void shouldNotCompleteWhenProductHasNoRecipe() {
        Product product = polo();
        ProductionOrder order = orderInStatus(product, 10, ProductionStatus.EN_PROCESO);
        when(orderRepository.findById(7L)).thenReturn(Optional.of(order));
        when(recipeRepository.findByProductId(1L)).thenReturn(List.of());

        assertThrows(IllegalArgumentException.class, () -> service.complete(7L));

        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void shouldCancelPendingOrder() {
        ProductionOrder order = orderInStatus(polo(), 5, ProductionStatus.PENDIENTE);
        when(orderRepository.findById(7L)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(ProductionOrder.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProductionOrderResponse response = service.cancel(7L);

        assertEquals(ProductionStatus.CANCELADA, response.getStatus());
    }

    @Test
    void shouldNotCancelFinishedOrder() {
        ProductionOrder order = orderInStatus(polo(), 5, ProductionStatus.FINALIZADA);
        when(orderRepository.findById(7L)).thenReturn(Optional.of(order));

        assertThrows(IllegalArgumentException.class, () -> service.cancel(7L));

        verify(orderRepository, never()).save(any(ProductionOrder.class));
    }
}
