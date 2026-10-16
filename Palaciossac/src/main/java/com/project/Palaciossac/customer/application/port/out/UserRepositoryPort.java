package com.project.Palaciossac.customer.application.port.out;

import com.project.Palaciossac.customer.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findById(Long id);
}
