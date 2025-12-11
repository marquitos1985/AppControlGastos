package com.AppDesarrollo.AppControlGastos.dtos;

import com.AppDesarrollo.AppControlGastos.entities.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CostItemResponse {



    private Long id;
    private String name;
    private Double amount;
    private Currency currency;
    private String description;
}
