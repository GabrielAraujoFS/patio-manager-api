package com.carros.data;
import com.carros.data.model.Carro;
import com.carros.data.model.CarroNotFoundException;
import com.carros.data.repository.CarroRepository;
import com.carros.data.service.CarroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarroServiceTest {
    @Mock
    private CarroRepository carroRepository;
    @InjectMocks
    private CarroService carroService;
    @Test
    void deveListarTodosOsCarros(){
        List<Carro> carrosFalsos = List.of(new Carro(), new Carro());
        when(carroRepository.findAll()).thenReturn(carrosFalsos);

        List<Carro> resultado = carroService.ListarTodos();

        assertEquals(2, resultado.size());
    }
    @Test
    void deveSalvarCarro() {
        // Arrange
        Carro carro = new Carro();
        carro.setModelo("Fusca");
        when(carroRepository.save(carro)).thenReturn(carro);

        // Act
        Carro resultado = carroService.salvarCarro(carro);

        // Assert
        assertEquals("Fusca", resultado.getModelo());
    }
    @Test
    void deveLancarExcecaoQuandoCarroNaoEncontrado() {
        // Arrange
        UUID idInexistente = UUID.randomUUID();
        when(carroRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CarroNotFoundException.class, () -> {
            carroService.deletarCarro(idInexistente);
        });
    }
}
