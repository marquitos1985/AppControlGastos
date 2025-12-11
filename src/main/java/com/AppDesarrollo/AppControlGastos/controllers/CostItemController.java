package com.AppDesarrollo.AppControlGastos.controllers;


import com.AppDesarrollo.AppControlGastos.dtos.CostItemRequest;
import com.AppDesarrollo.AppControlGastos.dtos.CostItemResponse;
import com.AppDesarrollo.AppControlGastos.services.CostItemServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cost-item")
@AllArgsConstructor
public class CostItemController {

    private CostItemServiceInterface  costItemService;

    @GetMapping("/{costId}")
    public ResponseEntity<List<CostItemResponse>> getCostItemByCostId(@PathVariable Long costId) {

        List<CostItemResponse> costItemResponseList = this.costItemService.getByCostId(costId);

        return ResponseEntity.ok(costItemResponseList);
    }

    @PostMapping("/{costId}")
    public ResponseEntity<CostItemResponse> create(@RequestBody CostItemRequest costItemRequest, @PathVariable Long costId) {

        CostItemResponse costItemResponse = this.costItemService.create(costItemRequest, costId);

        return ResponseEntity.ok(costItemResponse);


    }

    @PutMapping("{costItemId}")
    public ResponseEntity<CostItemResponse> update(@RequestBody CostItemRequest costItemRequest, @PathVariable Long costItemId){
        CostItemResponse costItemResponse = this.costItemService.update(costItemRequest, costItemId);

        return ResponseEntity.ok(costItemResponse);

    }

    @DeleteMapping("/{costId}/{costItemId}")
    public ResponseEntity<String> delete(@PathVariable Long costId, @PathVariable Long costItemId){

        this.costItemService.delete(costId, costItemId);

        return ResponseEntity.ok("Cost item deleted...");




    }


}
