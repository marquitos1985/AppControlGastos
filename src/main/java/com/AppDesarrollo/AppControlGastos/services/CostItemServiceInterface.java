package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.CostItemRequest;
import com.AppDesarrollo.AppControlGastos.dtos.CostItemResponse;

import java.util.List;

public interface CostItemServiceInterface {


    List<CostItemResponse> getByCostId(Long costId);
    CostItemResponse create(CostItemRequest costItemRequest, Long costId);
    CostItemResponse update(CostItemRequest costItemRequest, Long costItemId);
    void  delete(Long costId, Long costItemId);
}
