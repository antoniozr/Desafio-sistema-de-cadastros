package com.example.desafio_sistema_cadastro.dto;

public class UsuarioDto {
    private String nome;

    public UsuarioDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
