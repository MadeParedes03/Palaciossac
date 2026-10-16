package com.project.Palaciossac.customer.application.port.out;

import com.project.Palaciossac.customer.domain.model.Role;

public interface TokenIssuerPort {
    String issue(String email, Role role);
}
