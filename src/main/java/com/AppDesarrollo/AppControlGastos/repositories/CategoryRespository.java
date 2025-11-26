package com.AppDesarrollo.AppControlGastos.repositories;

import com.AppDesarrollo.AppControlGastos.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRespository extends JpaRepository<Category, Long> {


}
