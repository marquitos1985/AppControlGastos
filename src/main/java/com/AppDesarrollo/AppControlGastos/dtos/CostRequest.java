package com.AppDesarrollo.AppControlGastos.dtos;

import com.AppDesarrollo.AppControlGastos.entities.Category;
import com.AppDesarrollo.AppControlGastos.entities.enums.Currency;
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
public class CostRequest {

    private String name;
    private LocalDate expirationDate;
    private Double amount;
    private Currency currency;
    private Long categoryId;//categoryId == 0 ES SIN CATEGORIA


}
