package com.project.Palaciossac.customer.application.port.out;

import com.project.Palaciossac.customer.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryPort {
    Customer save(Customer customer);
    List<Customer> findAll();
    Optional<Customer> findById(Long id);
    Optional<Customer> findByDocument(String document);
    boolean existsByDocument(String document);
    void deleteById(Long id);
}
