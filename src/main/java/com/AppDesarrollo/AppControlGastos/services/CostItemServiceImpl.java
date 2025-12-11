package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.CostItemRequest;
import com.AppDesarrollo.AppControlGastos.dtos.CostItemResponse;
import com.AppDesarrollo.AppControlGastos.dtos.CostRequest;
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

    @Override
    public CostItemResponse create(CostItemRequest costItemRequest, Long costId) {
        Cost cost = this.costRepository.findById(costId)
                .orElseThrow(() -> new NotFoundException("Cost id not found: " + costId));

        CostItem costItem = this.costItemRequestToCostItem(costItemRequest);

        CostItemResponse costItemResponse = new CostItemResponse();

        cost.getCostItemList().add(costItem);

        costItem = this.costItemRepository.save(costItem);
        this.costRepository.save(cost);

        costItemResponse = this.costItemToCostItemResponse(costItem);


        return costItemResponse;
    }

    @Override
    public CostItemResponse update(CostItemRequest costItemRequest, Long costItemId) {
        CostItem costItem = this.costItemRepository.findById(costItemId)
                .orElseThrow(() -> new NotFoundException("Cost item id not found: " + costItemId));

        CostItemResponse costItemResponse = new CostItemResponse();

        CostItem newCostItem = this.costItemRequestToCostItem(costItemRequest);
        newCostItem.setId(costItem.getId());

        costItem = this.costItemRepository.save(newCostItem);

        costItemResponse = this.costItemToCostItemResponse(costItem);

        return costItemResponse;
    }

    @Override
    public void delete(Long costId, Long costItemId) {

        Cost cost = this.costRepository.findById(costId)
                .orElseThrow(() -> new NotFoundException("Cost id not found: " + costId));

        CostItem costItem = this.costItemRepository.findById(costItemId)
                .orElseThrow(() -> new NotFoundException("Cost item id not found: " + costItemId));

        if(this.existsIntoCostItemList(cost, costItem)){
            cost.getCostItemList().remove(costItem);
            this.costItemRepository.deleteById(costItemId);

            this.costRepository.save(cost);

        }else {

            throw new NotFoundException("Cost item id: " + costItemId + "not found into cost id: " + costId);
        }






    }


    private CostItemResponse costItemToCostItemResponse(CostItem costItem){

        CostItemResponse costItemResponse = new CostItemResponse();
        BeanUtils.copyProperties(costItem, costItemResponse);

        return costItemResponse;



    }

    private CostItem costItemRequestToCostItem(CostItemRequest costItemRequest){
        CostItem costItem = new CostItem();

        BeanUtils.copyProperties(costItemRequest, costItem);

        return costItem;

    }

    private boolean existsIntoCostItemList(Cost cost, CostItem costItem){

        return cost.getCostItemList().contains(costItem);

    }


}
