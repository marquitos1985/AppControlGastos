package com.AppDesarrollo.AppControlGastos.controllers;

import com.AppDesarrollo.AppControlGastos.exceptions.AlreadyExistsException;
import com.AppDesarrollo.AppControlGastos.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Map<String, Object>> NotFoundExceptionHandler(NotFoundException ex){
        final var response = new HashMap<String, Object>();
        response.put("code", HttpStatus.NOT_FOUND.value());//código
        response.put("status", HttpStatus.NOT_FOUND.getReasonPhrase());
        response.put("message", ex.getMessage());//mensaje adjunto del error

        return ResponseEntity.badRequest().body(response);

    }


    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> AlreadyExistsExeptionHandler(AlreadyExistsException ex){
        final var response = new HashMap<String, Object>();
        response.put("code", HttpStatus.BAD_REQUEST.value());//código
        response.put("status", HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.put("message", ex.getMessage());//mensaje adjunto del error

        return ResponseEntity.badRequest().body(response);

    }


}
