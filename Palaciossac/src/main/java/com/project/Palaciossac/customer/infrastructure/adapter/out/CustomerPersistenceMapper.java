package com.project.Palaciossac.customer.infrastructure.adapter.out;

import com.project.Palaciossac.customer.domain.model.Customer;
import com.project.Palaciossac.customer.infrastructure.entities.CustomerEntity;

public class CustomerPersistenceMapper {

    public static CustomerEntity toCustomerEntity(Customer customer) {
        CustomerEntity entity = new CustomerEntity(
                customer.getName(),
                customer.getDocument(),
                customer.getPhone(),
                customer.getAddress()
        );
        entity.setId(customer.getId());
        return entity;
    }

    public static Customer toCustomer(CustomerEntity entity) {
        return new Customer(
                entity.getId(),
                entity.getName(),
                entity.getDocument(),
                entity.getPhone(),
                entity.getAddress()
        );
    }

}
