package com.example.desafio_sistema_cadastro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "perguntas")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String descricao;

    public Pergunta() {
    }

    public Pergunta(String descricão) {
        this.descricao = descricão;
    }

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
