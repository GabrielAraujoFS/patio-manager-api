package com.carros.data.controller;


import com.carros.data.model.Carro;
import com.carros.data.service.CarroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/Carro")

public class CarroController {
    private final CarroService carroService;
    public CarroController (CarroService carroService){
        this.carroService = carroService;
    }
    @GetMapping
    public List<Carro> ListarCarros(){
        return carroService.ListarTodos();
    }
    @PostMapping
    public Carro criarCarro(@Valid @RequestBody Carro novoCarro){
        return carroService.salvarCarro(novoCarro);
    }
    @DeleteMapping("/{id}")
    public void deletarCarro(@PathVariable UUID id){
        carroService.deletarCarro(id);
    }
    @PutMapping("/{id}")
    public Carro atualizarCarro(@PathVariable UUID id, @RequestBody Carro carroNovo){
        return carroService.atualizarCarro(id, carroNovo);

    }
}
