package com.AppDesarrollo.AppControlGastos.repositories;

import com.AppDesarrollo.AppControlGastos.entities.CostOrigin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostOriginRepository extends JpaRepository<CostOrigin, Long> {



}
