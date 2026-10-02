package com.project.Palaciossac.repository;

import com.project.Palaciossac.entity.ProductionOrder;
import com.project.Palaciossac.entity.ProductionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductionOrderRepository extends JpaRepository<ProductionOrder, Long> {

    List<ProductionOrder> findByStatus(ProductionStatus status);

    List<ProductionOrder> findByEmployeeId(Long employeeId);

    @Query("SELECT o FROM ProductionOrder o WHERE o.status IN :statuses ORDER BY o.startDate")
    List<ProductionOrder> findByStatuses(@Param("statuses") List<ProductionStatus> statuses);
}
