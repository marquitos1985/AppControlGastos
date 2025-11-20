package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.CostRequest;
import com.AppDesarrollo.AppControlGastos.dtos.CostResponse;
import com.AppDesarrollo.AppControlGastos.entities.Cost;
import com.AppDesarrollo.AppControlGastos.repositories.CostRepository;
import org.springframework.beans.BeanUtils;

public class CostServiceImpl implements CostServiceInterface {

    private CostRepository costRepository;
    private CategoryServiceImpl categoryService;





    private CostResponse costToCostResponse(Cost cost){

        CostResponse costResponse = new CostResponse();
        BeanUtils.copyProperties(cost, costResponse);
        /*
        CostResponse costResponse = CostResponse.builder()
                .id(cost.getId())
                .name(cost.getName())
                .expirationDate(cost.getExpirationDate())
                .currency(cost.getCurrency())
                .amount(cost.getAmount())
                .build();


         */
        return costResponse;
    }

}
