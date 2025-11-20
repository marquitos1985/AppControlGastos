package com.AppDesarrollo.AppControlGastos.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CostOriginResponse {


    private Long id;
    private String name;
    private String description;

    //private List<CostResponse> costResponseList;
}
