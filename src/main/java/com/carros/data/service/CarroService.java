package com.carros.data.service;

import com.carros.data.model.Carro;
import com.carros.data.model.CarroNotFoundException;
import com.carros.data.model.StatusCarro;
import com.carros.data.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public void deletarCarro (UUID id){
        Carro carroExistente = carroRepository.findById(id)
            .orElseThrow(()-> new CarroNotFoundException("Carro não encontrado com o id: " + id));
        carroRepository.deleteById(id);
    }
    public Carro atualizarCarro(UUID id, Carro carroNovo) {
        Carro carroExistente = carroRepository.findById(id)
                        .orElseThrow(()-> new CarroNotFoundException("Carro não encontrado com o id: " + id));
        carroExistente.setModelo(carroNovo.getModelo());
        carroExistente.setPlaca(carroNovo.getPlaca());
        carroExistente.setCor(carroNovo.getCor());
        carroExistente.setStatus(carroNovo.getStatus());
        return carroRepository.save(carroExistente);
    }
}
