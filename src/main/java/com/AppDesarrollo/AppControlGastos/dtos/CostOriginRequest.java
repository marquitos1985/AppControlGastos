package com.AppDesarrollo.AppControlGastos.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CostOriginRequest {

    private String name;
    private String description;






}
