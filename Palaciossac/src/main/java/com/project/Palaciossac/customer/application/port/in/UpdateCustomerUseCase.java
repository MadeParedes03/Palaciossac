package com.project.Palaciossac.customer.application.port.in;

import com.project.Palaciossac.customer.domain.model.Customer;

public interface UpdateCustomerUseCase {
    Customer update(Long id, UpdateCustomerCommand cmd);
}
