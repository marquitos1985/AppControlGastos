package com.AppDesarrollo.AppControlGastos.services;

import com.AppDesarrollo.AppControlGastos.dtos.IncomeRequest;
import com.AppDesarrollo.AppControlGastos.dtos.IncomeResponse;
import com.AppDesarrollo.AppControlGastos.entities.CostOrigin;
import com.AppDesarrollo.AppControlGastos.entities.Income;
import com.AppDesarrollo.AppControlGastos.entities.User;
import com.AppDesarrollo.AppControlGastos.entities.enums.Currency;
import com.AppDesarrollo.AppControlGastos.exceptions.AlreadyExistsException;
import com.AppDesarrollo.AppControlGastos.exceptions.NotFoundException;
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
public class IncomeServiceImpl implements IncomeServiceInterface{

    private IncomeRepository incomeRepository;
    private UserRepository userRepository;

    @Override
    public IncomeResponse create(IncomeRequest incomeRequest, Long userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User id not found: " + userId));

        Income income = this.incomeRequestToIncome(incomeRequest);
        IncomeResponse incomeResponse = null;

            if(!this.existsIncomeIntoUser(user, income)){
                this.incomeRepository.save(income);
                user.getIncomeList().add(income);
                this.userRepository.save(user);

            }else {
                throw new AlreadyExistsException("Income already exists in user");
            }


        incomeResponse = this.incomeToIncomeResponse(income);


        return incomeResponse;
    }

    @Override
    public List<IncomeResponse> findByUserId(Long userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User id not found: " + userId));
        List<Income> incomeList = user.getIncomeList();
        List<IncomeResponse> incomeResponseList = incomeList.stream().map(income -> {
            return this.incomeToIncomeResponse(income);
        }).toList();

        return incomeResponseList;
    }

    @Override
    public IncomeResponse update(IncomeRequest incomeRequest, Long incomeId, Long userId) {

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User id not found: " + userId));
        Income income = this.incomeRepository.findById(incomeId)
                .orElseThrow(()-> new NotFoundException("Income id not found: " + incomeId));

        Income incomeUpdated = new Income();
        IncomeResponse incomeResponse = new IncomeResponse();



            if(user.getIncomeList().stream().anyMatch(income1 -> income1.getId().equals(incomeId))){

                incomeUpdated = this.incomeRequestToIncome(incomeRequest);
                incomeUpdated.setId(incomeId);
                //incomeUpdated.setCostOriginList(income.getCostOriginList());
                this.incomeRepository.save(incomeUpdated);

                incomeResponse = this.incomeToIncomeResponse(incomeUpdated);


            }else {
                throw new NotFoundException("Income id not found: " + incomeId + " into " + userId);
            }

        return incomeResponse;
    }

    @Override
    public void delete(Long incomeId, Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User id not found: " + userId));
        Income income = this.incomeRepository.findById(incomeId).orElseThrow(()-> new NotFoundException("Income id not found: " + incomeId));


            if(user.getIncomeList().stream().anyMatch(income1 -> income1.getId().equals(incomeId))){

                user.getIncomeList().remove(income);
                this.userRepository.save(user);

            }else {
                throw new NotFoundException("Income id not found: " + incomeId + " into " + userId);
            }





    }


    private Income incomeRequestToIncome(IncomeRequest incomeRequest){

        //Currency currency = this.stringToCurrency(incomeRequest.getCurrency());
        Currency currency = incomeRequest.getCurrency();

        Income income = Income.builder()
                .name(incomeRequest.getName())
                .incomeType(incomeRequest.getIncomeType())
                .amount(incomeRequest.getAmount())
                .currency(currency)
                .entryDate(incomeRequest.getEntryDate())
                .description(incomeRequest.getDescription())
                //.costOriginList(new ArrayList<CostOrigin>())
                .build();

        return income;


    }

    /*
    private Currency stringToCurrency(String c){

        Currency currency = null;
        try{
            currency = Currency.valueOf(c.toUpperCase());

        }catch (Exception ex){
            throw new IllegalArgumentException("Currency: " + c + " illegal.");
        }


        return currency;
    }

     */

    private IncomeResponse incomeToIncomeResponse(Income income){

        IncomeResponse incomeResponse = new IncomeResponse();
        BeanUtils.copyProperties(income, incomeResponse);

        /*
        IncomeResponse incomeResponse = IncomeResponse
                .builder()
                .id(income.getId())
                .name(income.getName())
                .entryDate(income.getEntryDate())
                .incomeType(income.getIncomeType())
                .amount(income.getAmount())
                .currency(income.getCurrency())
                .description(income.getDescription())
                .build();


         */


        return incomeResponse;

    }


    private boolean existsIncomeIntoUser(User user, Income income){
        List<Income> incomeList = user.getIncomeList().stream().filter(inc -> inc.getName().equals(income.getName())).toList();
        boolean out = true;
        if(incomeList.isEmpty()){
            out = false;
        }

        return out;

    }


}
