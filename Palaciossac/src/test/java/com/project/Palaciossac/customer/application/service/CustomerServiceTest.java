package com.project.Palaciossac.customer.application.service;

import com.project.Palaciossac.customer.application.port.in.CreateCustomerCommand;
import com.project.Palaciossac.customer.application.port.in.UpdateCustomerCommand;
import com.project.Palaciossac.customer.application.port.out.CustomerRepositoryPort;
import com.project.Palaciossac.customer.domain.exception.CustomerNotFoundException;
import com.project.Palaciossac.customer.domain.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepositoryPort repository;

    @InjectMocks
    CustomerService customerService;

    // AAA - > Arrange, Act , Assert

    @Test
    void shouldThrowWhenCustomerNotExists() {
        // Arrange
        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(CustomerNotFoundException.class,
                () -> customerService.findById(99L));
    }

    @Test
    void shouldReturnCustomerIfExists() {
        // Arrange
        Long customerId = 10L;
        Customer customer = new Customer(customerId, "Fiorella", "45678912", "999111222", "Av. Lima 123");
        when(repository.findById(customerId))
                .thenReturn(Optional.of(customer));

        // Act
        Customer valueReturned = customerService.findById(customerId);

        // Assert
        assertNotNull(valueReturned);
        assertNotNull(valueReturned.getId());
        assertEquals("Fiorella", valueReturned.getName());

        // matchers eq, any
        verify(repository).findById(eq(customerId));
        verify(repository, never()).save(any(Customer.class));
    }

    @Test
    void shouldSaveCustomerOnce() {
        Long customerId = 10L;
        Customer customer = new Customer(customerId, "Fiorella", "45678912", "999111222", "Av. Lima 123");

        when(repository.save(any(Customer.class)))
                .thenReturn(customer);

        customerService.create(new CreateCustomerCommand("Fiorella", "45678912", "999111222", "Av. Lima 123"));
        verify(repository, times(1))
                .save(any(Customer.class));
    }

    @Test
    void shouldRejectDuplicatedDocumentOnCreate() {
        when(repository.existsByDocument("45678912")).thenReturn(true);

        assertThrows(IllegalArgumentException.class,
                () -> customerService.create(new CreateCustomerCommand("Otra Persona", "45678912", null, null)));

        verify(repository, never()).save(any(Customer.class));
    }

    @Test
    void shouldUpdateExistingCustomer() {
        Customer existing = new Customer(10L, "Fiorella", "45678912", "999111222", "Av. Lima 123");
        when(repository.findById(10L)).thenReturn(Optional.of(existing));
        when(repository.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Customer updated = customerService.update(10L,
                new UpdateCustomerCommand("Fiorella Díaz", "45678912", "900000000", "Jr. Nuevo 456"));

        assertEquals("Fiorella Díaz", updated.getName());
        assertEquals("900000000", updated.getPhone());
        assertEquals("Jr. Nuevo 456", updated.getAddress());
        // el documento no cambió, no hace falta consultar si ya existe
        verify(repository, never()).existsByDocument(anyString());
    }

    @Test
    void shouldRejectUpdateWhenNewDocumentBelongsToAnotherCustomer() {
        Customer existing = new Customer(10L, "Fiorella", "45678912", null, null);
        when(repository.findById(10L)).thenReturn(Optional.of(existing));
        when(repository.existsByDocument("11111111")).thenReturn(true);

        assertThrows(IllegalArgumentException.class,
                () -> customerService.update(10L, new UpdateCustomerCommand("Fiorella", "11111111", null, null)));

        verify(repository, never()).save(any(Customer.class));
    }

    @Test
    void shouldDeleteExistingCustomer() {
        when(repository.findById(10L))
                .thenReturn(Optional.of(new Customer(10L, "Fiorella", "45678912", null, null)));

        customerService.delete(10L);

        verify(repository, times(1)).deleteById(10L);
    }

    @Test
    void shouldNotDeleteWhenCustomerNotExists() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.delete(99L));

        verify(repository, never()).deleteById(anyLong());
    }
}
