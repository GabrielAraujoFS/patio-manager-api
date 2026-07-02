package com.carros.data.service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.carros.data.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service

public class TokenService {
    @Value("${api.security.token.secret}")
    private String token;
    public  String gerarToken(Usuario usuarioDetails){
        try{
            Algorithm algoritmo = Algorithm.HMAC256(token);

            return JWT.create()
                    .withIssuer("patio-manager-api")
                    .withSubject(usuarioDetails.getUsername())
                    .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                    .sign(algoritmo);

        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }
    public String validarToken(String tokenJWT) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(token);
            return JWT.require(algoritmo)
                    .withIssuer("patio-manager-api")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
