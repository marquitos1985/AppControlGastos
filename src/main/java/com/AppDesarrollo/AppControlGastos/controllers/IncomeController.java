package com.AppDesarrollo.AppControlGastos.controllers;

import com.AppDesarrollo.AppControlGastos.dtos.IncomeRequest;
import com.AppDesarrollo.AppControlGastos.dtos.IncomeResponse;
import com.AppDesarrollo.AppControlGastos.services.IncomeServiceImpl;
import com.AppDesarrollo.AppControlGastos.services.IncomeServiceInterface;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/income")
@AllArgsConstructor
public class IncomeController {

    private IncomeServiceInterface incomeService;


    @PostMapping("/{userId}")
    public ResponseEntity<IncomeResponse> postIncome(@RequestBody IncomeRequest incomeRequest, @PathVariable Long userId){

        IncomeResponse incomeResponse = this.incomeService.create(incomeRequest, userId);

        return ResponseEntity.ok(incomeResponse);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<IncomeResponse>> getIncomesByUserId(@PathVariable Long userId){

       List<IncomeResponse> incomeResponseList =  this.incomeService.findByUserId(userId);

        return ResponseEntity.ok(incomeResponseList);


    }

    @PutMapping("/{incomeId}/{userId}")
    public ResponseEntity<IncomeResponse> update(@RequestBody IncomeRequest incomeRequest,
                                                 @PathVariable Long incomeId,
                                                 @PathVariable Long userId){


        IncomeResponse incomeResponse = this.incomeService.update(incomeRequest, incomeId, userId);

        return ResponseEntity.ok(incomeResponse);

    }

    @DeleteMapping("/{incomeId}/{userId}")
    public ResponseEntity<String> delete(@PathVariable Long incomeId,
                                         @PathVariable Long userId){

        this.incomeService.delete(incomeId, userId);

        return ResponseEntity.ok("Income deleted...");


    }

}
