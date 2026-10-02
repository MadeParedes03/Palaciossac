package com.project.Palaciossac.controller;

import com.project.Palaciossac.dto.SaleRequest;
import com.project.Palaciossac.dto.SaleResponse;
import com.project.Palaciossac.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaleResponse create(@Valid @RequestBody SaleRequest request) {
        return saleService.create(request);
    }

    @GetMapping
    public List<SaleResponse> findAll() {
        return saleService.findAll();
    }

    @GetMapping("/{id}")
    public SaleResponse findById(@PathVariable Long id) {
        return saleService.findById(id);
    }

    @GetMapping("/by-customer-document")
    public List<SaleResponse> findByCustomerDocument(@RequestParam String document) {
        return saleService.findByCustomerDocument(document);
    }

    @GetMapping("/by-product-type")
    public List<SaleResponse> findByProductType(@RequestParam String type) {
        return saleService.findByProductType(type);
    }

    @GetMapping("/by-date-range")
    public List<SaleResponse> findByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return saleService.findByDateRange(from, to);
    }
}
