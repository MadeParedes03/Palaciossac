package com.project.Palaciossac.customer.application.port.out;

public interface CredentialsAuthenticatorPort {

    /**
     * @throws com.taskhub.domain.exception.InvalidCredentialsException if the credentials are wrong
     */
    AuthenticatedUser authenticate(String email, String rawPassword);

}
