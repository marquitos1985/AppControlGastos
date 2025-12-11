package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.CostRequest;
import com.AppDesarrollo.AppControlGastos.dtos.CostResponse;
import com.AppDesarrollo.AppControlGastos.entities.Category;
import com.AppDesarrollo.AppControlGastos.entities.Cost;
import com.AppDesarrollo.AppControlGastos.entities.CostItem;
import com.AppDesarrollo.AppControlGastos.entities.CostOrigin;
import com.AppDesarrollo.AppControlGastos.exceptions.AlreadyExistsException;
import com.AppDesarrollo.AppControlGastos.exceptions.NotFoundException;
import com.AppDesarrollo.AppControlGastos.repositories.CategoryRespository;
import com.AppDesarrollo.AppControlGastos.repositories.CostOriginRepository;
import com.AppDesarrollo.AppControlGastos.repositories.CostRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CostServiceImpl implements CostServiceInterface {

    private CostRepository costRepository;
    private CategoryRespository categoryRespository;
    private CostOriginRepository costOriginRepository;


    private static final Category noneCategory = Category.builder()
            .name("NONE")
            .description("NONE")
            .build();

    @Override
    public List<CostResponse> getByCostOriginId(Long costOriginId) {

        CostOrigin costOrigin = this.costOriginRepository.findById(costOriginId)
                .orElseThrow(() -> new NotFoundException("Cost origin id not found: " + costOriginId));

        List<Cost> costList = costOrigin.getCostList();

        List<CostResponse> costResponseList = costList.stream().map(cost -> {
           return this.costToCostResponse(cost);
        }).toList();


        return costResponseList;
    }

    @Override
    public CostResponse create(CostRequest costRequest, Long costOriginId) {
        CostOrigin costOrigin = this.costOriginRepository.findById(costOriginId)
                .orElseThrow(() -> new NotFoundException("Cost origin id not found: " + costOriginId));

        Cost cost = this.costRequestToCost(costRequest);

        CostResponse costResponse = new CostResponse();

        if(!this.costAlreadyExistsInCostOrigin(cost, costOrigin)){


            cost = this.costRepository.save(cost);

            costOrigin.getCostList().add(cost);
            this.costOriginRepository.save(costOrigin);


        }else {
            throw new AlreadyExistsException("Cost name: " + costRequest.getName() + " already exists into cost origin id: "+ costOriginId);


        }

        costResponse = this.costToCostResponse(cost);


        return costResponse;
    }

    @Override
    public CostResponse update(CostRequest costRequest, Long costOriginId, Long costId) {

        CostOrigin costOrigin = this.costOriginRepository.findById(costOriginId)
                .orElseThrow(() -> new NotFoundException("Cost origin id not found: " + costOriginId));

        Cost cost = this.costRepository.findById(costId)
                .orElseThrow(() -> new NotFoundException("Cost id not found: " + costId));

        Cost newCost = new Cost();

        CostResponse costResponse = new CostResponse();

        if(this.costAlreadyExistsInCostOrigin(cost, costOrigin)){

            newCost = this.costRequestToCost(costRequest);
            newCost.setId(cost.getId());
            newCost.setCostItemList(cost.getCostItemList());

            newCost = this.costRepository.save(newCost);





        }else {
            throw new NotFoundException("Cost Id: " + costId + " not found into cost origin id: "+ costOriginId);



        }


        costResponse = this.costToCostResponse(newCost);



        return costResponse;
    }

    @Override
    public void delete(Long costOriginId, Long costId) {

        CostOrigin costOrigin = this.costOriginRepository.findById(costOriginId)
                .orElseThrow(() -> new NotFoundException("Cost origin id not found: " + costOriginId));

        Cost cost = this.costRepository.findById(costId)
                .orElseThrow(() -> new NotFoundException("Cost id not found: " + costId));


        if(this.costAlreadyExistsInCostOrigin(cost, costOrigin)){

            costOrigin.getCostList().remove(cost);

            this.costRepository.delete(cost);

            //this.costOriginRepository.save(costOrigin);



        }else {
            throw new NotFoundException("Cost Id: " + costId + " not found into cost origin id: "+ costOriginId);



        }




    }


    private CostResponse costToCostResponse(Cost cost){

        CostResponse costResponse = new CostResponse();
        BeanUtils.copyProperties(cost, costResponse);

        return costResponse;
    }

    private Cost costRequestToCost(CostRequest costRequest){

        Category category = new Category();


        if (costRequest.getCategoryId() != 0){//sin categoría
            category = this.categoryRespository.findById(costRequest.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category id not found: " + costRequest.getCategoryId()));

        }


        Cost cost = Cost.builder()
                .name(costRequest.getName())
                .amount(costRequest.getAmount())
                .currency(costRequest.getCurrency())
                .expirationDate(costRequest.getExpirationDate())
                .costItemList(new ArrayList<CostItem>())
                .category(category)
                .build();

        return cost;

    }

    private boolean costAlreadyExistsInCostOrigin(Cost cost, CostOrigin costOrigin){

       return   costOrigin.getCostList().stream()
               .anyMatch(cost1 -> cost1.getName().toUpperCase().equals(cost.getName().toUpperCase()));

    }





}


