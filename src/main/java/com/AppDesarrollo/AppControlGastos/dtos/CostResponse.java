package com.AppDesarrollo.AppControlGastos.dtos;

import com.AppDesarrollo.AppControlGastos.entities.Category;
import com.AppDesarrollo.AppControlGastos.entities.CostItem;
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
public class CostResponse {


    private Long id;
    private String name;
    private LocalDateTime expirationDate;
    private Double amount;
    private Currency currency;


    private List<CostItemResponse> costItemResponseList;

    private Category category;
}
