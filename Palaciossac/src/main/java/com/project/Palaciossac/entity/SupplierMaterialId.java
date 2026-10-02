package com.project.Palaciossac.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SupplierMaterialId implements Serializable {

    @Column(name = "id_proveedor")
    private Long supplierId;

    @Column(name = "id_insumo")
    private Long materialId;

    public SupplierMaterialId() {
    }

    public SupplierMaterialId(Long supplierId, Long materialId) {
        this.supplierId = supplierId;
        this.materialId = materialId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SupplierMaterialId that)) return false;
        return Objects.equals(supplierId, that.supplierId) && Objects.equals(materialId, that.materialId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierId, materialId);
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }
}
