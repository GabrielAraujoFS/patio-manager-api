package com.carros.data.service;

import com.carros.data.model.Carro;
import com.carros.data.model.StatusCarro;
import com.carros.data.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CarroService {
    private final CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository ){
        this.carroRepository = carroRepository;
    }
    public List<Carro> ListarTodos(){
        return carroRepository.findAll();
    }
    public List<Carro> ListarStatus(StatusCarro status){
        return carroRepository.findCarroByStatus(status);
    }
    public Carro salvarCarro (Carro carro){
        return carroRepository.save(carro);
    }
}
