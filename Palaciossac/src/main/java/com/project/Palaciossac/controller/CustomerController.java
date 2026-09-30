package com.project.Palaciossac.controller;

import com.project.Palaciossac.dto.CustomerRequest;
import com.project.Palaciossac.entity.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerController customerController;

    public CustomerController(CustomerController customerController) {
        this.customerController = customerController;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerController create(@Valid @RequestBody CustomerRequest request){
        return
    }
}
