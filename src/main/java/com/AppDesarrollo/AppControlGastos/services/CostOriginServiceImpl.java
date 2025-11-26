package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.CostOriginResponse;
import com.AppDesarrollo.AppControlGastos.entities.CostOrigin;
import com.AppDesarrollo.AppControlGastos.repositories.CostOriginRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CostOriginServiceImpl implements CostOriginServiceInterface{


    private CostOriginRepository costOriginRepository;






    private CostOriginResponse costOriginToCostOriginResponse(CostOrigin costOrigin){

        CostOriginResponse costOriginResponse = new CostOriginResponse();
        BeanUtils.copyProperties(costOrigin, costOriginResponse);


        /*
        CostOriginResponse costOriginResponse = CostOriginResponse.builder()
                .id(costOrigin.getId())
                .name(costOrigin.getName())
                .description(costOrigin.getDescription())
                .build();


         */
        return costOriginResponse;



    }
}
