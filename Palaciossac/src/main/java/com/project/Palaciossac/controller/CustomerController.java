package com.project.Palaciossac.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerController customerController;

    public CustomerController(CustomerController customerController) {
        this.customerController = customerController;
    }

}
