package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.CostRequest;
import com.AppDesarrollo.AppControlGastos.dtos.CostResponse;
import com.AppDesarrollo.AppControlGastos.entities.Cost;

import java.util.List;

public interface CostServiceInterface {

    List<CostResponse> getByCostOriginId(Long costOriginId);
    CostResponse create(CostRequest costRequest, Long costOriginId);
    CostResponse update(CostRequest costRequest, Long costId, Long costOriginId);
    void delete(Long costOriginId, Long costId);



}
