package com.AppDesarrollo.AppControlGastos.dtos;

import com.AppDesarrollo.AppControlGastos.entities.CostOrigin;
import com.AppDesarrollo.AppControlGastos.entities.enums.IncomeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomeResponse {



    private Long id;
    private String name;
    private IncomeType incomeType;
    private Double amount;
    private Currency currency;
    private LocalDateTime entryDate;
    private String description;

    //private List<CostOriginResponse> costOriginResponseList;
}
