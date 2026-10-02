package com.project.Palaciossac.customer.application.port.in;

import com.project.Palaciossac.customer.domain.model.Customer;

public interface CreateCustomerUseCase {
    Customer create(CreateCustomerCommand cmd);
}
