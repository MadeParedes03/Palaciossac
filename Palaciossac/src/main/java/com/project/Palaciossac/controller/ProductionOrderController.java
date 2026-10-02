package com.project.Palaciossac.controller;

import com.project.Palaciossac.dto.ProductionOrderRequest;
import com.project.Palaciossac.dto.ProductionOrderResponse;
import com.project.Palaciossac.entity.ProductionStatus;
import com.project.Palaciossac.service.ProductionOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/production-orders")
public class ProductionOrderController {

    private final ProductionOrderService productionOrderService;

    public ProductionOrderController(ProductionOrderService productionOrderService) {
        this.productionOrderService = productionOrderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductionOrderResponse create(@Valid @RequestBody ProductionOrderRequest request) {
        return productionOrderService.create(request);
    }

    @GetMapping
    public List<ProductionOrderResponse> findAll() {
        return productionOrderService.findAll();
    }

    @GetMapping("/{id}")
    public ProductionOrderResponse findById(@PathVariable Long id) {
        return productionOrderService.findById(id);
    }

    @GetMapping("/by-status")
    public List<ProductionOrderResponse> findByStatus(@RequestParam ProductionStatus status) {
        return productionOrderService.findByStatus(status);
    }

    @GetMapping("/by-employee/{employeeId}")
    public List<ProductionOrderResponse> findByEmployee(@PathVariable Long employeeId) {
        return productionOrderService.findByEmployee(employeeId);
    }

    @GetMapping("/active")
    public List<ProductionOrderResponse> findActive() {
        return productionOrderService.findActive();
    }

    @PatchMapping("/{id}/start")
    public ProductionOrderResponse start(@PathVariable Long id) {
        return productionOrderService.start(id);
    }

    @PatchMapping("/{id}/complete")
    public ProductionOrderResponse complete(@PathVariable Long id) {
        return productionOrderService.complete(id);
    }

    @PatchMapping("/{id}/cancel")
    public ProductionOrderResponse cancel(@PathVariable Long id) {
        return productionOrderService.cancel(id);
    }
}
