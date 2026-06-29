package com.carros.data.controller;

import com.carros.data.model.CarroNotFoundException;
import com.carros.data.model.ErroResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CarroNotFoundException.class)
    public ResponseEntity<ErroResponse>TratamentoCarro(CarroNotFoundException ex){
        ErroResponse erro = new ErroResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratamentoValidacao(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(field -> field.getField() + ": " + field.getDefaultMessage())
                .collect(Collectors.joining(", "));
        ErroResponse erro = new ErroResponse(HttpStatus.BAD_REQUEST.value(), mensagem);
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }
}
