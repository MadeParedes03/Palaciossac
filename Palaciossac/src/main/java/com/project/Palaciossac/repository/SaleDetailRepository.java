package com.project.Palaciossac.repository;

import com.project.Palaciossac.entity.SaleDetail;
import com.project.Palaciossac.entity.SaleDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, SaleDetailId> {
}
