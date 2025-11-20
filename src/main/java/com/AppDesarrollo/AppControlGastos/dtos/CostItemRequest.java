package com.AppDesarrollo.AppControlGastos.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Currency;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CostItemRequest {

    private String name;
    private Double amount;
    private Currency currency;
    private String description;

}
