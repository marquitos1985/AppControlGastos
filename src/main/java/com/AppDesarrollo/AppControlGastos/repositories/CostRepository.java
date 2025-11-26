package com.AppDesarrollo.AppControlGastos.repositories;

import com.AppDesarrollo.AppControlGastos.entities.Cost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostRepository extends JpaRepository<Cost, Long> {


}
