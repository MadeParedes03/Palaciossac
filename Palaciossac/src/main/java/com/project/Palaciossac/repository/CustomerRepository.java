package com.project.Palaciossac.repository;

import com.project.Palaciossac.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  CustomerRepository extends JpaRepository<Customer, Long> {
        Optional<Customer> findByDocument(String document);

}
