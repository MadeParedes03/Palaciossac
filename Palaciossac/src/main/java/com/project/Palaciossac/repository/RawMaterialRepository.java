package com.project.Palaciossac.repository;

import com.project.Palaciossac.entity.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {

    List<RawMaterial> findByNameContainingIgnoreCase(String name);

    @Query("SELECT m FROM RawMaterial m WHERE m.stock < :threshold ORDER BY m.stock ASC")
    List<RawMaterial> findWithStockBelow(@Param("threshold") BigDecimal threshold);
}
