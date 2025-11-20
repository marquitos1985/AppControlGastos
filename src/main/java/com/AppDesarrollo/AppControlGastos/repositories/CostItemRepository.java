package com.AppDesarrollo.AppControlGastos.repositories;

import com.AppDesarrollo.AppControlGastos.entities.CostItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostItemRepository extends JpaRepository<CostItem, Long> {


}
