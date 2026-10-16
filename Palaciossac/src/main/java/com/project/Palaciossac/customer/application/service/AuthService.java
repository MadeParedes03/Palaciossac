package com.project.Palaciossac.customer.application.service;


import com.project.Palaciossac.customer.application.port.in.LoginUseCase;
import com.project.Palaciossac.customer.application.port.in.RegisterUseCase;
import com.project.Palaciossac.customer.application.port.out.CredentialsAuthenticatorPort;
import com.project.Palaciossac.customer.application.port.out.PasswordHasherPort;
import com.project.Palaciossac.customer.application.port.out.TokenIssuerPort;
import com.project.Palaciossac.customer.application.port.out.UserRepositoryPort;
import com.project.Palaciossac.customer.domain.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService implements RegisterUseCase, LoginUseCase {

    private final UserRepositoryPort users;
    private final PasswordHasherPort passwordHasher;
    private final CredentialsAuthenticatorPort authenticator;
    private final TokenIssuerPort tokenIssuer;

    public AuthService(UserRepositoryPort users, PasswordHasherPort passwordHasher,
                       CredentialsAuthenticatorPort authenticator, TokenIssuerPort tokenIssuer) {
        this.users = users;
        this.passwordHasher = passwordHasher;
        this.authenticator = authenticator;
        this.tokenIssuer = tokenIssuer;
    }

    @Override
    @Transactional
    public String register(String name, String email, String rawPassword) {
        if (users.existsByEmail(email)) {
            throw new EmailAlreadyUsedException(email);
        }
        User saved = users.save(User.register(name, email, passwordHasher.hash(rawPassword)));
        return tokenIssuer.issue(saved.getEmail(), saved.getRole());
    }

    @Override
    public String login(String email, String rawPassword) {
        AuthenticatedUser user = authenticator.authenticate(email, rawPassword);
        return tokenIssuer.issue(email, user.role());
    }
}