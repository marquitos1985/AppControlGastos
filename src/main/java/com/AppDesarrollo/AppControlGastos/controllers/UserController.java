package com.AppDesarrollo.AppControlGastos.controllers;

import com.AppDesarrollo.AppControlGastos.dtos.UserRequest;
import com.AppDesarrollo.AppControlGastos.dtos.UserResponse;
import com.AppDesarrollo.AppControlGastos.entities.User;
import com.AppDesarrollo.AppControlGastos.services.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {

    private UserServiceInterface userService;



    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAll(){
        List<UserResponse> userResponseList = this.userService.findAll();


        return ResponseEntity.ok(userResponseList);
    }



    /*
    @GetMapping("/all")
    public ResponseEntity<String> getAll(){
        List<UserResponse> userResponseList = this.userService.findAll();


        return ResponseEntity.ok("userResponseList");
    }


     */

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        UserResponse userResponse = this.userService.create(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest, @PathVariable Long id){
        UserResponse userResponse = this.userService.update(userRequest, id);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        this.userService.deleteById(id);
        return ResponseEntity.ok("User deleted...");
    }




}
