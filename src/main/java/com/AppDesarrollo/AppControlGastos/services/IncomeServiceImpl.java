package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.IncomeResponse;
import com.AppDesarrollo.AppControlGastos.entities.CostOrigin;
import com.AppDesarrollo.AppControlGastos.entities.Income;
import com.AppDesarrollo.AppControlGastos.repositories.CostOriginRepository;
import com.AppDesarrollo.AppControlGastos.repositories.IncomeRepository;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class IncomeServiceImpl implements IncomeServiceInterface{

    private IncomeRepository incomeRepository;
    private CostOriginServiceImpl costOriginService;

    @Override
    public List<Income> findAllByUserId(Long userId) {
        List<Income> incomeList = this.incomeRepository.findAll();



        return List.of();
    }








    private IncomeResponse IncomeToIncomeResponse(Income income){

        IncomeResponse incomeResponse = new IncomeResponse();
        BeanUtils.copyProperties(income, incomeResponse);

        /*
        IncomeResponse incomeResponse = IncomeResponse
                .builder()
                .id(income.getId())
                .name(income.getName())
                .entryDate(income.getEntryDate())
                .incomeType(income.getIncomeType())
                .amount(income.getAmount())
                .currency(income.getCurrency())
                .description(income.getDescription())
                .build();


         */


        return incomeResponse;

    }
}
