package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.CostResponse;
import com.AppDesarrollo.AppControlGastos.entities.Cost;
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
