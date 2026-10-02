package com.project.Palaciossac.repository;

import com.project.Palaciossac.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByCustomerDocument(String document);

    List<Sale> findBySaleDateBetween(LocalDateTime from, LocalDateTime to);

    @Query("SELECT DISTINCT s FROM Sale s " +
            "JOIN s.details d " +
            "JOIN d.product p " +
            "WHERE p.type = :type")
    List<Sale> findSalesByProductType(@Param("type") String type);
}
