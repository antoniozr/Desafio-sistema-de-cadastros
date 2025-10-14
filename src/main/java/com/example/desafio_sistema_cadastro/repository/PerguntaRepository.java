package com.example.desafio_sistema_cadastro.repository;

import com.example.desafio_sistema_cadastro.model.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
}
