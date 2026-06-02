package com.carros.data.repository;

import com.carros.data.model.Carro;
import com.carros.data.model.StatusCarro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CarroRepository extends JpaRepository<Carro, UUID>{
    List<Carro> findCarroByModelo(String modelo);
    List<Carro> findCarroByStatus(StatusCarro status);

}
