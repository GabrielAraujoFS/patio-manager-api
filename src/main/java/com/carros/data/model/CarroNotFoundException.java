package com.carros.data.model;


public class CarroNotFoundException extends RuntimeException {
    public CarroNotFoundException(String message){
        super(message);
    }
}
