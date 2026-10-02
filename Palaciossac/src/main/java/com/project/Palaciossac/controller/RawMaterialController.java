package com.project.Palaciossac.controller;

import com.project.Palaciossac.dto.RawMaterialRequest;
import com.project.Palaciossac.dto.RawMaterialResponse;
import com.project.Palaciossac.service.RawMaterialService;
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

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/raw-materials")
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;

    public RawMaterialController(RawMaterialService rawMaterialService) {
        this.rawMaterialService = rawMaterialService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RawMaterialResponse create(@Valid @RequestBody RawMaterialRequest request) {
        return rawMaterialService.create(request);
    }

    @GetMapping
    public List<RawMaterialResponse> findAll() {
        return rawMaterialService.findAll();
    }

    @GetMapping("/{id}")
    public RawMaterialResponse findById(@PathVariable Long id) {
        return rawMaterialService.findById(id);
    }

    @PutMapping("/{id}")
    public RawMaterialResponse update(@PathVariable Long id, @Valid @RequestBody RawMaterialRequest request) {
        return rawMaterialService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        rawMaterialService.delete(id);
    }

    @GetMapping("/search")
    public List<RawMaterialResponse> searchByName(@RequestParam String name) {
        return rawMaterialService.searchByName(name);
    }

    @GetMapping("/low-stock")
    public List<RawMaterialResponse> findLowStock(@RequestParam BigDecimal threshold) {
        return rawMaterialService.findLowStock(threshold);
    }
}
