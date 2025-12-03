package com.AppDesarrollo.AppControlGastos.dtos;

import com.AppDesarrollo.AppControlGastos.entities.Category;
import com.AppDesarrollo.AppControlGastos.entities.CostItem;
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
public class CostResponse {


    private Long id;
    private String name;
    private LocalDate expirationDate;
    private Double amount;
    private Currency currency;
    //private CategoryResponse categoryResponse;

    //private List<CostItemResponse> costItemResponseList;


}
