package com.AppDesarrollo.AppControlGastos.dtos;

import com.AppDesarrollo.AppControlGastos.entities.Category;
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
public class CostRequest {

    private String name;
    private LocalDateTime expirationDate;
    private Double amount;
    private Currency currency;

    private List<CostItemRequest> costItemRequestList;

    private Category category;


}
