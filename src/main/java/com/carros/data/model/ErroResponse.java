package com.carros.data.model;

import lombok.Data;
@Data
public class ErroResponse {
    int status;
    String mensagem;
    public ErroResponse(int status, String mensagem){
        this.status = status;
        this.mensagem = mensagem;
    }
}
