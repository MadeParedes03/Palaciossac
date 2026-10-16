package com.project.Palaciossac.customer.application.port.out;

public interface PasswordHasherPort {
    String hash(String rawPassword);
}
