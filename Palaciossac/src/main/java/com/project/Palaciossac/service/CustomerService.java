package com.project.Palaciossac.service;

import com.project.Palaciossac.dto.CustomerRequest;
import com.project.Palaciossac.dto.CustomerResponse;
import com.project.Palaciossac.entity.Customer;
import com.project.Palaciossac.exception.ResourceNotFoundException;
import com.project.Palaciossac.repository.CustomerRepository;

import java.util.List;

public class CustomerService {
    private final CustomerRepository  customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public CustomerResponse create (CustomerRequest request){
        Customer customer = new Customer(request.getName(), request.getDocument(),
                request.getPhone(), request.getAddress());
            return toResponse(customerRepository.save(customer));
    }

    public List<CustomerResponse> findAll(){
        return customerRepository.findAll().stream().map(this::toResponse).toList();
    }

    public CustomerResponse update(Long id, CustomerRequest request) {
        Customer customer = getEntity(id);
        customer.setName(request.getName());
        customer.setDocument(request.getDocument());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        return toResponse(customerRepository.save(customer));
    }

    public void delete(Long id) {
        Customer customer = getEntity(id);
        customerRepository.delete(customer);
    }

    public Customer getEntity(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado: " + id));
    }

    private CustomerResponse toResponse(Customer c) {
        return new CustomerResponse(c.getIdCustomer(), c.getName(),c.getDocument(),c.getPhone(),c.getAddress());
    }


}
