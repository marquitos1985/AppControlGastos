package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.UserRequest;
import com.AppDesarrollo.AppControlGastos.dtos.UserResponse;
import com.AppDesarrollo.AppControlGastos.entities.CostOrigin;
import com.AppDesarrollo.AppControlGastos.entities.Income;
import com.AppDesarrollo.AppControlGastos.entities.User;
import com.AppDesarrollo.AppControlGastos.exceptions.AlreadyExistsException;
import com.AppDesarrollo.AppControlGastos.exceptions.NotFoundException;
import com.AppDesarrollo.AppControlGastos.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserServiceInterface{

    private UserRepository userRepository;

    @Override
    public UserResponse create(UserRequest userRequest) {

        UserResponse userResponse = null;
        User user = this.userRepository.findByEmail(userRequest.getEmail()).orElse(null);

        if(user == null){
            user = this.userRequestToUser(userRequest);
            this.userRepository.save(user);
        }else {
            throw new AlreadyExistsException("User email already exists: " + userRequest.getEmail());
        }

        userResponse = this.userToUserResponse(user);
        return userResponse;
    }

    @Override
    public void deleteById(Long id) {


        User user = this.userRepository.findById(id).orElse(null);

        log.info("USER ----------------------");



        if(user != null){
            log.info(user.toString());
            log.info("Deleting..........................................");
            this.userRepository.delete(user);


        }else {
            throw new NotFoundException("User id not found: " + id);
        }

    }

    @Override
    public UserResponse update(UserRequest userRequest, Long id) {

        User user = this.userRepository.findById(id).orElse(null);
        UserResponse userResponse = null;

        if(user != null){
            BeanUtils.copyProperties(userRequest, user);
            this.userRepository.save(user);


        }else {
            throw new NotFoundException("User id not found: " + id);
        }

        userResponse = this.userToUserResponse(user);


        return userResponse;
    }

    @Override
    public List<UserResponse> findAll() {

        List<UserResponse> userResponseList = this.userRepository.findAll()
                .stream()
                .map(user -> {
                  return this.userToUserResponse(user);

                }).toList();



        return userResponseList;
    }


    private UserResponse userToUserResponse(User user){

        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);

        return userResponse;


    }

    private User userRequestToUser(UserRequest userRequest){
        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .costOriginList(new ArrayList<CostOrigin>())
                .incomeList(new ArrayList<Income>())
                .build();


        return user;
    }


}
