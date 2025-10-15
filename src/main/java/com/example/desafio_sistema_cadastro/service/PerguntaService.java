package com.example.desafio_sistema_cadastro.service;

import com.example.desafio_sistema_cadastro.exceptions.OperacaoNaoPermitidaException;
import com.example.desafio_sistema_cadastro.exceptions.RecursoNaoEcontradoException;
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

    public Pergunta registrarPergunta(Pergunta pergunta) {
        return perguntaRepository.save(pergunta);
    }

    public void deletarPergunta(Long id) {

        if (id <=4 ) {
            throw new OperacaoNaoPermitidaException("As 4 primeiras perguntas não podem ser deletadas");
        }

        if (!perguntaRepository.existsById(id)){
            throw new RecursoNaoEcontradoException("Produto com id: " + id + " não encontrado");
        }
        perguntaRepository.deleteById(id);
    }
}
