package com.carros.data.controller;


import com.carros.data.model.Carro;
import com.carros.data.service.CarroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Carro criarCarro(@RequestBody Carro novoCarro){
        return carroService.salvarCarro(novoCarro);
    }
}
