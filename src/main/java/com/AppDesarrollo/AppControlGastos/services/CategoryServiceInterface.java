package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.CategoryResponse;

public interface CategoryServiceInterface {

    CategoryResponse findByCostId(Long costId);
}
