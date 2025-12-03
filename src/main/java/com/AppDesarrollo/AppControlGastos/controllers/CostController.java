package com.AppDesarrollo.AppControlGastos.controllers;

import com.AppDesarrollo.AppControlGastos.dtos.CostOriginRequest;
import com.AppDesarrollo.AppControlGastos.dtos.CostOriginResponse;
import com.AppDesarrollo.AppControlGastos.dtos.CostRequest;
import com.AppDesarrollo.AppControlGastos.dtos.CostResponse;
import com.AppDesarrollo.AppControlGastos.services.CostServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cost")
@AllArgsConstructor
public class CostController {

    private CostServiceInterface costService;

    @GetMapping("/{costOriginId}")
    public ResponseEntity<List<CostResponse>> getByCostOriginId(@PathVariable Long costOriginId){

        List<CostResponse> costResponseList = this.costService.getByCostOriginId(costOriginId);

        return ResponseEntity.ok(costResponseList);

    }

    @PostMapping("/{costOriginId}")
    public ResponseEntity<CostResponse> create(@RequestBody CostRequest costRequest, @PathVariable Long costOriginId){

        CostResponse costResponse = this.costService.create(costRequest, costOriginId);

        return ResponseEntity.ok(costResponse);




    }

    @PutMapping("/{costOriginId}/{costId}")
    public ResponseEntity<CostResponse> update(@RequestBody CostRequest costRequest,
                                                     @PathVariable Long costOriginId, @PathVariable Long costId){

        CostResponse costResponse = this.costService.update(costRequest,
                costOriginId, costId);
        return ResponseEntity.ok(costResponse);


    }

    @DeleteMapping("/{costOriginId}/{costId}")
    public ResponseEntity<String> delete(@PathVariable Long costOriginId,
                                         @PathVariable Long costId){

        this.costService.delete(costOriginId, costId);

        return ResponseEntity.ok("Cost origin deleted...");




    }


}
