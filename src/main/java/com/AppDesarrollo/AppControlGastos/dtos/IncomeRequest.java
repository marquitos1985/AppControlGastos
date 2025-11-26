package com.AppDesarrollo.AppControlGastos.dtos;

import com.AppDesarrollo.AppControlGastos.entities.enums.Currency;
import com.AppDesarrollo.AppControlGastos.entities.enums.IncomeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomeRequest {

    private String name;
    private IncomeType incomeType;
    private Double amount;
    private Currency currency;
    private LocalDate entryDate;
    private String description;


}
