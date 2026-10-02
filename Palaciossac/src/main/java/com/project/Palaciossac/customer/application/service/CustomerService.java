package com.project.Palaciossac.customer.application.service;

import com.project.Palaciossac.customer.application.port.in.CreateCustomerCommand;
import com.project.Palaciossac.customer.application.port.in.CreateCustomerUseCase;
import com.project.Palaciossac.customer.application.port.in.DeleteCustomerUseCase;
import com.project.Palaciossac.customer.application.port.in.GetCustomerUseCase;
import com.project.Palaciossac.customer.application.port.in.UpdateCustomerCommand;
import com.project.Palaciossac.customer.application.port.in.UpdateCustomerUseCase;
import com.project.Palaciossac.customer.application.port.out.CustomerRepositoryPort;
import com.project.Palaciossac.customer.domain.exception.CustomerNotFoundException;
import com.project.Palaciossac.customer.domain.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService
        implements CreateCustomerUseCase, GetCustomerUseCase, UpdateCustomerUseCase, DeleteCustomerUseCase {

    CustomerRepositoryPort repository; // puerto salida

    public CustomerService(CustomerRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Customer create(CreateCustomerCommand cmd) {
        if (cmd.getDocument() != null && this.repository.existsByDocument(cmd.getDocument()))
            throw new IllegalArgumentException("Ya existe un cliente con el documento " + cmd.getDocument());

        Customer cus = new Customer();
        cus.setName(cmd.getName());
        cus.setDocument(cmd.getDocument());
        cus.setPhone(cmd.getPhone());
        cus.setAddress(cmd.getAddress());

        return this.repository.save(cus);
    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> optionalCustomer = this.repository.findById(id);
        if (optionalCustomer.isEmpty())
            throw new CustomerNotFoundException("Cliente no encontrado con id " + id);
        return optionalCustomer.get();
    }

    @Override
    public Customer findByDocument(String document) {
        return this.repository.findByDocument(document)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente no encontrado con documento " + document));
    }

    @Override
    public List<Customer> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Customer update(Long id, UpdateCustomerCommand cmd) {
        Customer existing = this.findById(id);

        boolean documentChanged = cmd.getDocument() != null && !cmd.getDocument().equals(existing.getDocument());
        if (documentChanged && this.repository.existsByDocument(cmd.getDocument()))
            throw new IllegalArgumentException("Ya existe un cliente con el documento " + cmd.getDocument());

        existing.setName(cmd.getName());
        existing.setDocument(cmd.getDocument());
        existing.setPhone(cmd.getPhone());
        existing.setAddress(cmd.getAddress());

        return this.repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        this.findById(id); // lanza CustomerNotFoundException si no existe
        this.repository.deleteById(id);
    }
}
