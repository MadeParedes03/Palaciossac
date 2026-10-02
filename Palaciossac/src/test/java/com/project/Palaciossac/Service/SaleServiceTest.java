package com.project.Palaciossac.service;

import com.project.Palaciossac.customer.infrastructure.adapter.out.CustomerJpaRepository;
import com.project.Palaciossac.customer.infrastructure.entities.CustomerEntity;
import com.project.Palaciossac.dto.SaleItemRequest;
import com.project.Palaciossac.dto.SaleRequest;
import com.project.Palaciossac.dto.SaleResponse;
import com.project.Palaciossac.entity.Employee;
import com.project.Palaciossac.entity.Product;
import com.project.Palaciossac.entity.Sale;
import com.project.Palaciossac.exception.ResourceNotFoundException;
import com.project.Palaciossac.repository.EmployeeRepository;
import com.project.Palaciossac.repository.ProductRepository;
import com.project.Palaciossac.repository.SaleDetailRepository;
import com.project.Palaciossac.repository.SaleRepository;
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
public class SaleServiceTest {

    @Mock
    SaleRepository saleRepository;
    @Mock
    SaleDetailRepository saleDetailRepository;
    @Mock
    CustomerJpaRepository customerRepository;
    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    SaleService saleService;

    private SaleRequest requestWith(SaleItemRequest... items) {
        SaleRequest request = new SaleRequest();
        request.setCustomerId(1L);
        request.setEmployeeId(2L);
        request.setPaymentType("EFECTIVO");
        request.setItems(List.of(items));
        return request;
    }

    private SaleItemRequest item(Long productId, Integer quantity) {
        SaleItemRequest item = new SaleItemRequest();
        item.setProductId(productId);
        item.setQuantity(quantity);
        return item;
    }

    private Product product(Long id, String name, String price, int stock) {
        Product product = new Product(name, "Polo", "M", "Blanco", new BigDecimal(price), stock);
        product.setId(id);
        return product;
    }

    @Test
    void shouldCreateSaleDecreaseStockAndCalculateTotal() {
        // Arrange
        Product polo = product(3L, "Polo básico", "35.00", 10);
        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(new CustomerEntity("Ana Torres", "45678912", null, null)));
        when(employeeRepository.findById(2L)).thenReturn(Optional.of(new Employee("Rosa", "Ventas", null)));
        when(productRepository.findById(3L)).thenReturn(Optional.of(polo));
        when(saleRepository.save(any(Sale.class))).thenAnswer(invocation -> {
            Sale sale = invocation.getArgument(0);
            sale.setId(100L);
            return sale;
        });
        when(saleDetailRepository.saveAll(anyList())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        SaleResponse response = saleService.create(requestWith(item(3L, 2)));

        // Assert
        assertEquals(100L, response.getId());
        assertEquals("Ana Torres", response.getCustomerName());
        assertEquals(1, response.getItems().size());
        assertEquals(0, new BigDecimal("70.00").compareTo(response.getTotal()));
        assertEquals(8, polo.getStock());
        verify(productRepository).save(polo);
    }

    @Test
    void shouldRejectSaleWhenStockIsInsufficient() {
        Product polo = product(3L, "Polo básico", "35.00", 1);
        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(new CustomerEntity("Ana Torres", "45678912", null, null)));
        when(employeeRepository.findById(2L)).thenReturn(Optional.of(new Employee("Rosa", "Ventas", null)));
        when(productRepository.findById(3L)).thenReturn(Optional.of(polo));

        assertThrows(IllegalArgumentException.class,
                () -> saleService.create(requestWith(item(3L, 5))));

        assertEquals(1, polo.getStock());
        verify(saleRepository, never()).save(any(Sale.class));
    }

    @Test
    void shouldRejectSaleWithRepeatedProduct() {
        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(new CustomerEntity("Ana Torres", "45678912", null, null)));
        when(employeeRepository.findById(2L)).thenReturn(Optional.of(new Employee("Rosa", "Ventas", null)));

        assertThrows(IllegalArgumentException.class,
                () -> saleService.create(requestWith(item(3L, 1), item(3L, 2))));

        verify(productRepository, never()).findById(anyLong());
        verify(saleRepository, never()).save(any(Sale.class));
    }

    @Test
    void shouldThrowWhenCustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> saleService.create(requestWith(item(3L, 1))));

        verify(saleRepository, never()).save(any(Sale.class));
    }
}
