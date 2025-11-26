package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.IncomeRequest;
import com.AppDesarrollo.AppControlGastos.dtos.IncomeResponse;
import com.AppDesarrollo.AppControlGastos.entities.Income;

import java.util.List;

public interface IncomeServiceInterface{

    IncomeResponse create(IncomeRequest incomeRequest, Long userId);

    List<IncomeResponse> findByUserId(Long userId);

    IncomeResponse update(IncomeRequest incomeRequest, Long incomeId, Long userId);
    void delete(Long incomeId, Long userId);

}
