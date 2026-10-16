package com.project.Palaciossac.customer.application.port.out;

import com.project.Palaciossac.customer.domain.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepositoryPort {

    Task save(Task task);

    Optional<Task> findById(Long id);

    List<Task> findByOwnerId(Long ownerId);

    void deleteById(Long id);
}
