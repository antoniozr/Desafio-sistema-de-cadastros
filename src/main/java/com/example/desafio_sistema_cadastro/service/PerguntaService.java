package com.example.desafio_sistema_cadastro.service;

import com.example.desafio_sistema_cadastro.model.Pergunta;
import com.example.desafio_sistema_cadastro.repository.PerguntaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerguntaService {

    private PerguntaRepository perguntaRepository;

    public PerguntaService(PerguntaRepository perguntaRepository) {
        this.perguntaRepository = perguntaRepository;
    }

    public List<Pergunta> listarPerguntas() {
        return perguntaRepository.findAll();
    }
}
