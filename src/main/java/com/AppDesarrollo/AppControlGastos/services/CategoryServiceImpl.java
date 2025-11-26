package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.CategoryResponse;
import com.AppDesarrollo.AppControlGastos.entities.Category;
import com.AppDesarrollo.AppControlGastos.entities.Cost;
import com.AppDesarrollo.AppControlGastos.exceptions.NotFoundException;
import com.AppDesarrollo.AppControlGastos.repositories.CategoryRespository;
import com.AppDesarrollo.AppControlGastos.repositories.CostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryServiceInterface{

    private CategoryRespository categoryRespository;
    private CostRepository costRepository;


    @Override
    public CategoryResponse findByCostId(Long costId) {

        Cost cost = this.costRepository.findById(costId).orElse(null);
        Category category = null;
        CategoryResponse categoryResponse = null;
        if(cost != null){
            category = cost.getCategory();

            if (category != null){
                categoryResponse = this.categoryToCategoryResponse(category);
            }else {
                throw new NullPointerException("Category null for cost id = " + costId);

            }

        }else {
            throw new NotFoundException("Cost id not found: " + costId);
        }



        return categoryResponse;
    }






    private CategoryResponse categoryToCategoryResponse(Category category){
        CategoryResponse categoryResponse = new CategoryResponse();

        BeanUtils.copyProperties(category, categoryResponse);

        return categoryResponse;
    }


}
