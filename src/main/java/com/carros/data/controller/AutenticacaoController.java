package com.carros.data.controller;

import com.carros.data.model.LoginRequest;
import com.carros.data.model.Usuario;
import com.carros.data.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/auth")
@RestController
public class AutenticacaoController {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    public AutenticacaoController(TokenService tokenService, AuthenticationManager authenticationManager){
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        var autenticacao = new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getSenha());
        var auth = authenticationManager.authenticate(autenticacao);
        var usuario = (Usuario) auth.getPrincipal();
        var token = tokenService.gerarToken(usuario);
        return ResponseEntity.ok(token);
    }
}