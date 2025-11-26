package com.AppDesarrollo.AppControlGastos.repositories;

import com.AppDesarrollo.AppControlGastos.entities.CostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostItemRepository extends JpaRepository<CostItem, Long> {


}
