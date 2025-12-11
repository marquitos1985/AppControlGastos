package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.CostItemResponse;

import java.util.List;

public interface CostItemServiceInterface {


    List<CostItemResponse> getByCostId(Long costId);
}
