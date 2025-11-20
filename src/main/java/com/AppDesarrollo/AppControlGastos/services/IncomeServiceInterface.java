package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.entities.Income;

import java.util.List;

public interface IncomeServiceInterface{

    List<Income> findAllByUserId(Long userId);


}
