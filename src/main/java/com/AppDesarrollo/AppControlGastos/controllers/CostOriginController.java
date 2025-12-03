package com.AppDesarrollo.AppControlGastos.controllers;

import com.AppDesarrollo.AppControlGastos.dtos.CostOriginRequest;
import com.AppDesarrollo.AppControlGastos.dtos.CostOriginResponse;
import com.AppDesarrollo.AppControlGastos.services.CostOriginServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cost-origin")
@AllArgsConstructor
public class CostOriginController {

    private CostOriginServiceInterface costOriginService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CostOriginResponse>> getByUserId(@PathVariable Long userId){

        List<CostOriginResponse> costOriginResponseList = this.costOriginService.findByUserId(userId);


        return ResponseEntity.ok(costOriginResponseList);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<CostOriginResponse> create(@RequestBody CostOriginRequest costOriginRequest,
                                                     @PathVariable Long userId){

        CostOriginResponse costOriginResponse = this.costOriginService.create(costOriginRequest, userId);

        return ResponseEntity.ok(costOriginResponse);



    }

    @PutMapping("/{userId}/{costOriginId}")
    public ResponseEntity<CostOriginResponse> update(@RequestBody CostOriginRequest costOriginRequest,
                                                     @PathVariable Long userId, @PathVariable Long costOriginId){

        CostOriginResponse costOriginResponse = this.costOriginService.update(costOriginRequest,
                costOriginId, userId);
        return ResponseEntity.ok(costOriginResponse);


    }

    @DeleteMapping("/{userId}/{costOriginId}")
    public ResponseEntity<String> delete(@PathVariable Long costOriginId,
                                         @PathVariable Long userId){

        this.costOriginService.delete(costOriginId, userId);

        return ResponseEntity.ok("Cost origin deleted...");


    }










}
