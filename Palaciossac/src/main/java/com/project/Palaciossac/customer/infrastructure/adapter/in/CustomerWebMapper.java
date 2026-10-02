package com.project.Palaciossac.customer.infrastructure.adapter.in;

import com.project.Palaciossac.customer.application.port.in.CreateCustomerCommand;
import com.project.Palaciossac.customer.application.port.in.UpdateCustomerCommand;
import com.project.Palaciossac.customer.domain.model.Customer;

import java.util.List;

public class CustomerWebMapper {

    public static CustomerDto toCustomerDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getDocument(),
                customer.getPhone(),
                customer.getAddress()
        );
    }

    public static List<CustomerDto> toListOfCustomerDto(List<Customer> customerList) {
        return customerList.stream()
                .map(customer -> toCustomerDto(customer))
                .toList();
    }

    public static CreateCustomerCommand toCommand(CustomerDto request) {
        return new CreateCustomerCommand(
                request.getName(), request.getDocument(), request.getPhone(), request.getAddress());
    }

    public static UpdateCustomerCommand toUpdateCommand(CustomerDto request) {
        return new UpdateCustomerCommand(
                request.getName(), request.getDocument(), request.getPhone(), request.getAddress());
    }
}
