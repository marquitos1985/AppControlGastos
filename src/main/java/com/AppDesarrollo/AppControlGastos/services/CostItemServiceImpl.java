package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.CostItemResponse;
import com.AppDesarrollo.AppControlGastos.entities.CostItem;
import org.springframework.beans.BeanUtils;

public class CostItemServiceImpl implements CostItemServiceInterface{



    private CostItemResponse costItemToCostItemResponse(CostItem costItem){

        CostItemResponse costItemResponse = new CostItemResponse();
        BeanUtils.copyProperties(costItem, costItemResponse);

        return costItemResponse;



    }
}
