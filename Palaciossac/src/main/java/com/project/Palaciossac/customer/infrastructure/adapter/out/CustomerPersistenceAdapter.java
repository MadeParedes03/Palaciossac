package com.project.Palaciossac.customer.infrastructure.adapter.out;

import com.project.Palaciossac.customer.application.port.out.CustomerRepositoryPort;
import com.project.Palaciossac.customer.domain.model.Customer;
import com.project.Palaciossac.customer.infrastructure.entities.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerPersistenceAdapter implements CustomerRepositoryPort {

    private final CustomerJpaRepository customerJpaRepository;

    public CustomerPersistenceAdapter(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entitySaved = customerJpaRepository.save(CustomerPersistenceMapper.toCustomerEntity(customer));
        return CustomerPersistenceMapper.toCustomer(entitySaved);
    }

    @Override
    public List<Customer> findAll() {
        return customerJpaRepository.findAll().stream()
                .map(CustomerPersistenceMapper::toCustomer)
                .toList();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return this.customerJpaRepository.findById(id).map(CustomerPersistenceMapper::toCustomer);
    }

    @Override
    public Optional<Customer> findByDocument(String document) {
        return this.customerJpaRepository.findByDocument(document).map(CustomerPersistenceMapper::toCustomer);
    }

    @Override
    public boolean existsByDocument(String document) {
        return this.customerJpaRepository.existsByDocument(document);
    }

    @Override
    public void deleteById(Long id) {
        this.customerJpaRepository.deleteById(id);
    }
}
