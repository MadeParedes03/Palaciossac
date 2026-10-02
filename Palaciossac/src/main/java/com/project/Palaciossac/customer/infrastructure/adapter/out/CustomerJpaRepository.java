package com.project.Palaciossac.customer.infrastructure.adapter.out;

import com.project.Palaciossac.customer.infrastructure.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// adaptador de MySQL por medio de JPA
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByDocument(String document);

    boolean existsByDocument(String document);
}
