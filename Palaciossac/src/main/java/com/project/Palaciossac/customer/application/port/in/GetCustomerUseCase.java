package com.project.Palaciossac.customer.application.port.in;

import com.project.Palaciossac.customer.domain.model.Customer;

import java.util.List;

public interface GetCustomerUseCase {
    Customer findById(Long id);
    Customer findByDocument(String document);
    List<Customer> findAll();
}
