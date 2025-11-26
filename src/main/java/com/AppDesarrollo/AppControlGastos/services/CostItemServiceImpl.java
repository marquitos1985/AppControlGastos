package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.CostItemResponse;
import com.AppDesarrollo.AppControlGastos.entities.CostItem;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CostItemServiceImpl implements CostItemServiceInterface{



    private CostItemResponse costItemToCostItemResponse(CostItem costItem){

        CostItemResponse costItemResponse = new CostItemResponse();
        BeanUtils.copyProperties(costItem, costItemResponse);

        return costItemResponse;



    }
}
