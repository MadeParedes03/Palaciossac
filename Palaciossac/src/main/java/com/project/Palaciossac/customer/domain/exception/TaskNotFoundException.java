package com.project.Palaciossac.customer.domain.exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long id) {
        super("Task not found: " + id);
    }
}
