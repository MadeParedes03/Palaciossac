package com.project.Palaciossac.service;

import com.project.Palaciossac.dto.SupplierRequest;
import com.project.Palaciossac.dto.SupplierResponse;
import com.project.Palaciossac.entity.Supplier;
import com.project.Palaciossac.exception.ResourceNotFoundException;
import com.project.Palaciossac.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    public SupplierResponse create(SupplierRequest request) {
        Supplier supplier = new Supplier(request.getName(), request.getPhone(),
                request.getEmail(), request.getAddress());
        return toResponse(supplierRepository.save(supplier));
    }

    public List<SupplierResponse> findAll() {
        return supplierRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public SupplierResponse findById(Long id) {
        return toResponse(getEntity(id));
    }

    public List<SupplierResponse> searchByName(String name) {
        return supplierRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public SupplierResponse update(Long id, SupplierRequest request) {
        Supplier supplier = getEntity(id);
        supplier.setName(request.getName());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        supplier.setAddress(request.getAddress());
        return toResponse(supplierRepository.save(supplier));
    }

    @Transactional
    public void delete(Long id) {
        supplierRepository.delete(getEntity(id));
    }

    private Supplier getEntity(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado: " + id));
    }

    private SupplierResponse toResponse(Supplier s) {
        return new SupplierResponse(s.getId(), s.getName(), s.getPhone(), s.getEmail(), s.getAddress());
    }
}
