package com.project.Palaciossac.service;

import com.project.Palaciossac.dto.RawMaterialRequest;
import com.project.Palaciossac.dto.RawMaterialResponse;
import com.project.Palaciossac.entity.RawMaterial;
import com.project.Palaciossac.exception.ResourceNotFoundException;
import com.project.Palaciossac.repository.RawMaterialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RawMaterialService {

    private final RawMaterialRepository rawMaterialRepository;

    public RawMaterialService(RawMaterialRepository rawMaterialRepository) {
        this.rawMaterialRepository = rawMaterialRepository;
    }

    @Transactional
    public RawMaterialResponse create(RawMaterialRequest request) {
        RawMaterial material = new RawMaterial(request.getName(), request.getUnitOfMeasure(), request.getStock());
        return toResponse(rawMaterialRepository.save(material));
    }

    public List<RawMaterialResponse> findAll() {
        return rawMaterialRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public RawMaterialResponse findById(Long id) {
        return toResponse(getEntity(id));
    }

    public List<RawMaterialResponse> searchByName(String name) {
        return rawMaterialRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::toResponse)
                .toList();
    }

    // Insumos por debajo de un umbral: sirve para saber qué reponer
    public List<RawMaterialResponse> findLowStock(BigDecimal threshold) {
        return rawMaterialRepository.findWithStockBelow(threshold).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public RawMaterialResponse update(Long id, RawMaterialRequest request) {
        RawMaterial material = getEntity(id);
        material.setName(request.getName());
        material.setUnitOfMeasure(request.getUnitOfMeasure());
        material.setStock(request.getStock());
        return toResponse(rawMaterialRepository.save(material));
    }

    @Transactional
    public void delete(Long id) {
        rawMaterialRepository.delete(getEntity(id));
    }

    private RawMaterial getEntity(Long id) {
        return rawMaterialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insumo no encontrado: " + id));
    }

    private RawMaterialResponse toResponse(RawMaterial m) {
        return new RawMaterialResponse(m.getId(), m.getName(), m.getUnitOfMeasure(), m.getStock());
    }
}
