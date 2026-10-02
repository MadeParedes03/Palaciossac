package com.project.Palaciossac.repository;

import com.project.Palaciossac.entity.SupplierMaterial;
import com.project.Palaciossac.entity.SupplierMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierMaterialRepository extends JpaRepository<SupplierMaterial, SupplierMaterialId> {

    List<SupplierMaterial> findBySupplierId(Long supplierId);

    // Proveedores de un insumo, del más barato al más caro
    List<SupplierMaterial> findByRawMaterialIdOrderByPurchasePriceAsc(Long materialId);
}
