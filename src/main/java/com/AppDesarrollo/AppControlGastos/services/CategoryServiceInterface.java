package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.CategoryRequest;
import com.AppDesarrollo.AppControlGastos.dtos.CategoryResponse;

public interface CategoryServiceInterface {

    CategoryResponse findByCostId(Long costId);
    CategoryResponse create(CategoryRequest categoryRequest);
    CategoryResponse update(CategoryRequest categoryRequest, Long categoryId);
    void delete(Long costOriginId, Long categoryId);
}
