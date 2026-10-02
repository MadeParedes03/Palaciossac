package com.project.Palaciossac.service;

import com.project.Palaciossac.dto.SupplierMaterialRequest;
import com.project.Palaciossac.dto.SupplierMaterialResponse;
import com.project.Palaciossac.dto.SupplierMaterialUpdateRequest;
import com.project.Palaciossac.entity.RawMaterial;
import com.project.Palaciossac.entity.Supplier;
import com.project.Palaciossac.entity.SupplierMaterial;
import com.project.Palaciossac.entity.SupplierMaterialId;
import com.project.Palaciossac.exception.ResourceNotFoundException;
import com.project.Palaciossac.repository.RawMaterialRepository;
import com.project.Palaciossac.repository.SupplierMaterialRepository;
import com.project.Palaciossac.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SupplierMaterialService {

    private final SupplierMaterialRepository supplierMaterialRepository;
    private final SupplierRepository supplierRepository;
    private final RawMaterialRepository rawMaterialRepository;

    public SupplierMaterialService(SupplierMaterialRepository supplierMaterialRepository,
                                   SupplierRepository supplierRepository,
                                   RawMaterialRepository rawMaterialRepository) {
        this.supplierMaterialRepository = supplierMaterialRepository;
        this.supplierRepository = supplierRepository;
        this.rawMaterialRepository = rawMaterialRepository;
    }

    @Transactional
    public SupplierMaterialResponse create(SupplierMaterialRequest request) {
        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado: " + request.getSupplierId()));
        RawMaterial material = rawMaterialRepository.findById(request.getMaterialId())
                .orElseThrow(() -> new ResourceNotFoundException("Insumo no encontrado: " + request.getMaterialId()));

        SupplierMaterialId id = new SupplierMaterialId(supplier.getId(), material.getId());
        if (supplierMaterialRepository.existsById(id)) {
            throw new IllegalArgumentException("El proveedor '" + supplier.getName()
                    + "' ya tiene registrado el insumo '" + material.getName() + "'");
        }

        SupplierMaterial link = new SupplierMaterial(supplier, material, request.getPurchasePrice());
        return toResponse(supplierMaterialRepository.save(link));
    }

    public List<SupplierMaterialResponse> findAll() {
        return supplierMaterialRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public List<SupplierMaterialResponse> findBySupplier(Long supplierId) {
        return supplierMaterialRepository.findBySupplierId(supplierId).stream()
                .map(this::toResponse)
                .toList();
    }

    // Del proveedor más barato al más caro para un insumo
    public List<SupplierMaterialResponse> findByMaterialCheapestFirst(Long materialId) {
        return supplierMaterialRepository.findByRawMaterialIdOrderByPurchasePriceAsc(materialId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public SupplierMaterialResponse updatePrice(Long supplierId, Long materialId,
                                                SupplierMaterialUpdateRequest request) {
        SupplierMaterial link = getEntity(supplierId, materialId);
        link.setPurchasePrice(request.getPurchasePrice());
        return toResponse(supplierMaterialRepository.save(link));
    }

    @Transactional
    public void delete(Long supplierId, Long materialId) {
        supplierMaterialRepository.delete(getEntity(supplierId, materialId));
    }

    private SupplierMaterial getEntity(Long supplierId, Long materialId) {
        return supplierMaterialRepository.findById(new SupplierMaterialId(supplierId, materialId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe la relación proveedor " + supplierId + " - insumo " + materialId));
    }

    private SupplierMaterialResponse toResponse(SupplierMaterial sm) {
        return new SupplierMaterialResponse(
                sm.getSupplier().getId(), sm.getSupplier().getName(),
                sm.getRawMaterial().getId(), sm.getRawMaterial().getName(),
                sm.getPurchasePrice());
    }
}
