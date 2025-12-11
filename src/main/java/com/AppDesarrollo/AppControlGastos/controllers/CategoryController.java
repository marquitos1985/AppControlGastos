package com.AppDesarrollo.AppControlGastos.controllers;


import com.AppDesarrollo.AppControlGastos.dtos.CategoryRequest;
import com.AppDesarrollo.AppControlGastos.dtos.CategoryResponse;
import com.AppDesarrollo.AppControlGastos.dtos.CostItemResponse;
import com.AppDesarrollo.AppControlGastos.repositories.CategoryRespository;
import com.AppDesarrollo.AppControlGastos.services.CategoryServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {


    private CategoryServiceInterface categoryService;


    @GetMapping("/{costId}")
    public ResponseEntity<CategoryResponse> getByCostId(@PathVariable Long costId){

        CategoryResponse categoryResponse = this.categoryService.findByCostId(costId);

        return ResponseEntity.ok(categoryResponse);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest categoryRequest){
        CategoryResponse categoryResponse = this.categoryService.create(categoryRequest);

        return ResponseEntity.ok(categoryResponse);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> update(@RequestBody CategoryRequest categoryRequest,
                                                   @PathVariable Long categoryId){

        CategoryResponse categoryResponse = this.categoryService.update(categoryRequest, categoryId);

        return ResponseEntity.ok(categoryResponse);

    }


    @DeleteMapping("/{costOriginId}/{categoryId}")
    public ResponseEntity<String> delete(@PathVariable Long costOriginId, @PathVariable Long categoryId){

        this.categoryService.delete(costOriginId, categoryId);

        return ResponseEntity.ok("Category deleted...");


    }



}
