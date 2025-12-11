package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.CostItemResponse;
import com.AppDesarrollo.AppControlGastos.entities.Cost;
import com.AppDesarrollo.AppControlGastos.entities.CostItem;
import com.AppDesarrollo.AppControlGastos.exceptions.NotFoundException;
import com.AppDesarrollo.AppControlGastos.repositories.CostItemRepository;
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
public class CostItemServiceImpl implements CostItemServiceInterface{

    private CostItemRepository costItemRepository;
    private CostRepository costRepository;

    @Override
    public List<CostItemResponse> getByCostId(Long costId) {

        Cost cost = this.costRepository.findById(costId)
                .orElseThrow(() -> new NotFoundException("Cost id not found: " + costId));

        List<CostItem> costItemList = cost.getCostItemList();

        List<CostItemResponse> costItemResponseList = costItemList.stream().map(costItem -> {
            return this.costItemToCostItemResponse(costItem);
        }).toList();

        return costItemResponseList;

    }


    private CostItemResponse costItemToCostItemResponse(CostItem costItem){

        CostItemResponse costItemResponse = new CostItemResponse();
        BeanUtils.copyProperties(costItem, costItemResponse);

        return costItemResponse;



    }


}
