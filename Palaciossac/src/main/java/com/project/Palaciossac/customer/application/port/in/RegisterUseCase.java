package com.project.Palaciossac.customer.application.port.in;

public interface RegisterUseCase {
    /** Registers a new user (always with role USER) and returns an access token. */
    String register(String name, String email, String rawPassword);
}

