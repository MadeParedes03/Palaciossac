package com.project.Palaciossac.controller;

import com.project.Palaciossac.dto.SupplierMaterialRequest;
import com.project.Palaciossac.dto.SupplierMaterialResponse;
import com.project.Palaciossac.dto.SupplierMaterialUpdateRequest;
import com.project.Palaciossac.service.SupplierMaterialService;
import jakarta.validation.Valid;
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

import java.util.List;

@RestController
@RequestMapping("/api/supplier-materials")
public class SupplierMaterialController {

    private final SupplierMaterialService supplierMaterialService;

    public SupplierMaterialController(SupplierMaterialService supplierMaterialService) {
        this.supplierMaterialService = supplierMaterialService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierMaterialResponse create(@Valid @RequestBody SupplierMaterialRequest request) {
        return supplierMaterialService.create(request);
    }

    @GetMapping
    public List<SupplierMaterialResponse> findAll() {
        return supplierMaterialService.findAll();
    }

    @GetMapping("/by-supplier/{supplierId}")
    public List<SupplierMaterialResponse> findBySupplier(@PathVariable Long supplierId) {
        return supplierMaterialService.findBySupplier(supplierId);
    }

    @GetMapping("/by-material/{materialId}")
    public List<SupplierMaterialResponse> findByMaterial(@PathVariable Long materialId) {
        return supplierMaterialService.findByMaterialCheapestFirst(materialId);
    }

    @PutMapping("/{supplierId}/{materialId}")
    public SupplierMaterialResponse updatePrice(@PathVariable Long supplierId,
                                                @PathVariable Long materialId,
                                                @Valid @RequestBody SupplierMaterialUpdateRequest request) {
        return supplierMaterialService.updatePrice(supplierId, materialId, request);
    }

    @DeleteMapping("/{supplierId}/{materialId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long supplierId, @PathVariable Long materialId) {
        supplierMaterialService.delete(supplierId, materialId);
    }
}
