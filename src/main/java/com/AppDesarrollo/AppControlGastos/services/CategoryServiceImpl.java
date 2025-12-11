package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.CategoryRequest;
import com.AppDesarrollo.AppControlGastos.dtos.CategoryResponse;
import com.AppDesarrollo.AppControlGastos.entities.Category;
import com.AppDesarrollo.AppControlGastos.entities.Cost;
import com.AppDesarrollo.AppControlGastos.entities.CostOrigin;
import com.AppDesarrollo.AppControlGastos.exceptions.NotFoundException;
import com.AppDesarrollo.AppControlGastos.repositories.CategoryRespository;
import com.AppDesarrollo.AppControlGastos.repositories.CostOriginRepository;
import com.AppDesarrollo.AppControlGastos.repositories.CostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryServiceInterface{

    private CategoryRespository categoryRespository;
    private CostRepository costRepository;
    private CostOriginRepository costOriginRepository;


    @Override
    public CategoryResponse findByCostId(Long costId) {

        Cost cost = this.costRepository.findById(costId)
                .orElseThrow(() -> new NotFoundException("Cost id not found: " + costId));

        Category category = cost.getCategory();
        CategoryResponse categoryResponse = null;

        if (category != null){
            categoryResponse = this.categoryToCategoryResponse(category);
        }else {

            throw new NullPointerException("Category null for cost id = " + costId);

        }

        return categoryResponse;
    }

    @Override
    public CategoryResponse create(CategoryRequest categoryRequest) {


        Category category = this.categoryRequestToCategory(categoryRequest);
        CategoryResponse categoryResponse = new CategoryResponse();
        category = this.categoryRespository.save(category);

        categoryResponse = this.categoryToCategoryResponse(category);



        return categoryResponse;
    }

    @Override
    public CategoryResponse update(CategoryRequest categoryRequest, Long categoryId) {

        Category category = new Category();
        Category newCategory = new Category();
        CategoryResponse categoryResponse = new CategoryResponse();


            category = this.categoryRespository.findById(categoryId)
                    .orElseThrow(() -> new NotFoundException("Category id not found: " + categoryId ));
            newCategory = this.categoryRequestToCategory(categoryRequest);


            newCategory.setId(category.getId());

            newCategory = this.categoryRespository.save(newCategory);



        categoryResponse = this.categoryToCategoryResponse(newCategory);


        return categoryResponse;



    }


    @Override
    public void delete(Long costOriginId, Long categoryId) {
        Category category = this.categoryRespository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category id not found: " + categoryId ));

        CostOrigin costOrigin = this.costOriginRepository.findById(costOriginId)
                .orElseThrow(() -> new NotFoundException("Cost origin id not found: " + costOriginId ));


        costOrigin.getCostList().forEach(cost -> {
            if(cost.getCategory().getId().equals(categoryId)){
                costOrigin.getCostList().remove(cost);
            }

        });

        this.costOriginRepository.save(costOrigin);





    }




    private CategoryResponse categoryToCategoryResponse(Category category){


        CategoryResponse categoryResponse = new CategoryResponse();
        BeanUtils.copyProperties(category, categoryResponse);
        return categoryResponse;
    }

    private Category categoryRequestToCategory(CategoryRequest categoryRequest){

        Category category = new Category();
        BeanUtils.copyProperties(categoryRequest, category);

        return  category;



    }


    /*
    private List<Cost> findCostListByCategoryId(Long categoryId){
        List<Cost> costList = this.costRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new NotFoundException("Category id not found: " + categoryId ));

        return costList;


    }


     */




}
