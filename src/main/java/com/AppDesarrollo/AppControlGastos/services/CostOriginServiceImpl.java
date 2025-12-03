package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.CostOriginRequest;
import com.AppDesarrollo.AppControlGastos.dtos.CostOriginResponse;
import com.AppDesarrollo.AppControlGastos.entities.Cost;
import com.AppDesarrollo.AppControlGastos.entities.CostOrigin;
import com.AppDesarrollo.AppControlGastos.entities.Income;
import com.AppDesarrollo.AppControlGastos.entities.User;
import com.AppDesarrollo.AppControlGastos.exceptions.AlreadyExistsException;
import com.AppDesarrollo.AppControlGastos.exceptions.NotFoundException;
import com.AppDesarrollo.AppControlGastos.repositories.CostOriginRepository;
import com.AppDesarrollo.AppControlGastos.repositories.IncomeRepository;
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
public class CostOriginServiceImpl implements CostOriginServiceInterface{


    private CostOriginRepository costOriginRepository;
    private UserRepository userRepository;
    private IncomeRepository incomeRepository;

    @Override
    public List<CostOriginResponse> findByUserId(Long userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User id not found: " + userId));

        List<CostOrigin> costOriginList = user.getCostOriginList();
        List<CostOriginResponse> costOriginResponseList = costOriginList.stream().map(costOrigin -> {
           return this.costOriginToCostOriginResponse(costOrigin);

        }).toList();


        return costOriginResponseList;
    }

    @Override
    public CostOriginResponse create(CostOriginRequest costOriginRequest, Long userId) {

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User id not found: " + userId));

        CostOrigin costOrigin = this.costOriginRequestToCostOrigin(costOriginRequest);
        CostOriginResponse costOriginResponse = new CostOriginResponse();


        if (!this.existsIntoUser(costOrigin, user)){

            costOrigin = this.costOriginRepository.save(costOrigin);//lo devuelve con el id
            user.getCostOriginList().add(costOrigin);
            this.userRepository.save(user);


        }else {
            throw new AlreadyExistsException("Cost origin already exist in user id: " + userId);
        }

        costOriginResponse = this.costOriginToCostOriginResponse(costOrigin);



        return costOriginResponse;
    }

    @Override
    public CostOriginResponse update(CostOriginRequest costOriginRequest, Long costOriginId,Long userId) {

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User id not found: " + userId));

        CostOrigin costOrigin = this.costOriginRepository.findById(costOriginId)
                .orElseThrow( () -> new NotFoundException("CostOriginId not found: " + costOriginId));

        CostOrigin newCostOrigin = new CostOrigin();
        CostOriginResponse costOriginResponse = new CostOriginResponse();


        if(this.existsIntoUser(costOrigin,user)){
            newCostOrigin = this.updateCostOrigin(costOrigin, costOriginRequest);
            this.costOriginRepository.save(newCostOrigin);
            costOriginResponse = this.costOriginToCostOriginResponse(newCostOrigin);

        }else {
            throw new NotFoundException("Not found CostOriginId: " + costOriginId + " into userId: " + userId);

        }




        return costOriginResponse;
    }

    @Override
    public void delete(Long costOriginId, Long userId) {

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User id not found: " + userId));

        CostOrigin costOrigin = this.costOriginRepository.findById(costOriginId)
                .orElseThrow( () -> new NotFoundException("CostOriginId not found: " + costOriginId));


        if(this.existsIntoUser(costOrigin,user)){

            //this.costOriginRepository.deleteById(costOrigin.getId());
            user.getCostOriginList().remove(costOrigin);
            this.userRepository.save(user);



        }else {
            throw new NotFoundException("Not found CostOriginId: " + costOriginId + " into userId: " + userId);

        }




    }


    private CostOriginResponse costOriginToCostOriginResponse(CostOrigin costOrigin){

        CostOriginResponse costOriginResponse = new CostOriginResponse();
        BeanUtils.copyProperties(costOrigin, costOriginResponse);


        /*
        CostOriginResponse costOriginResponse = CostOriginResponse.builder()
                .id(costOrigin.getId())
                .name(costOrigin.getName())
                .description(costOrigin.getDescription())
                .build();


         */
        return costOriginResponse;



    }

    private CostOrigin costOriginRequestToCostOrigin(CostOriginRequest costOriginRequest){
        CostOrigin costOrigin = CostOrigin.builder()
                .name(costOriginRequest.getName())
                .description(costOriginRequest.getDescription())
                .costList(new ArrayList<Cost>())
                .incomeList(new ArrayList<Income>())
                .build();

        return costOrigin;

    }

    private boolean existsIntoUser(CostOrigin costOrigin, User user){

        boolean out = user.getCostOriginList().stream()
                .anyMatch(costOrigin1 -> costOrigin1.getName().toUpperCase().equals(costOrigin.getName().toUpperCase()));

        return out;



    }

    CostOrigin updateCostOrigin(CostOrigin costOrigin, CostOriginRequest costOriginRequest){

        CostOrigin newCostOrigin = new CostOrigin(costOrigin.getId(),
                costOriginRequest.getName(), costOriginRequest.getDescription(),
                costOrigin.getCostList(), costOrigin.getIncomeList());

        return newCostOrigin;



    }



}
