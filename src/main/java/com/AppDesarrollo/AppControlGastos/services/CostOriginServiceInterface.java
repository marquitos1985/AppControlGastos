package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.CostOriginRequest;
import com.AppDesarrollo.AppControlGastos.dtos.CostOriginResponse;
import com.AppDesarrollo.AppControlGastos.entities.CostOrigin;
import com.AppDesarrollo.AppControlGastos.entities.Income;

import java.util.List;

public interface CostOriginServiceInterface {

    List<CostOriginResponse> findByUserId(Long userId);
    CostOriginResponse create(CostOriginRequest costOriginRequest, Long userId);
    CostOriginResponse update(CostOriginRequest costOriginRequest, Long costOriginId, Long userId);
    void delete(Long costOriginId, Long userId);

}
