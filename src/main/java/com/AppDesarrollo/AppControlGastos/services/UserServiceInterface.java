package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.UserRequest;
import com.AppDesarrollo.AppControlGastos.dtos.UserResponse;
import com.AppDesarrollo.AppControlGastos.repositories.UserRepository;

import java.util.List;

public interface UserServiceInterface {

    UserResponse create(UserRequest userRequest);
    void deleteById (Long id);
    UserResponse update(UserRequest userRequest, Long id);
    List<UserResponse> findAll();


}
