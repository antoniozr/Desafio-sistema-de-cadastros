package com.example.desafio_sistema_cadastro.service;

import com.example.desafio_sistema_cadastro.exceptions.OperacaoNaoPermitidaException;
import com.example.desafio_sistema_cadastro.exceptions.RecursoNaoEcontradoException;
import com.example.desafio_sistema_cadastro.model.Pergunta;
import com.example.desafio_sistema_cadastro.repository.PerguntaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PerguntaServiceTest {

    @InjectMocks
    private PerguntaService perguntaService;

    @Mock
    private PerguntaRepository perguntaRepository;

    @Test
    @DisplayName("Deve retornar uma lista com uma pergunta ")
    void deveRetornarUmalistaComUmaPergunta() {
        Pergunta pergunta = new Pergunta("Qual a primeira pergunta?");
        Mockito.when(perguntaRepository.findAll()).thenReturn(Collections.singletonList(pergunta));
        List<Pergunta> perguntas = perguntaService.listarPerguntas();

        Assertions.assertEquals(1, perguntas.size());
    }

    @Test
    @DisplayName("Deve registrar a pergunta com sucesso")
    void registrarPerguntaComSucesso() {
        Pergunta pergunta = new Pergunta("Qual a primeira pergunta?");
        Mockito.when(perguntaRepository.save(pergunta)).thenReturn(pergunta);
        Pergunta resultado = perguntaService.registrarPergunta(pergunta);

        Assertions.assertEquals(pergunta, resultado);
        Mockito.verify(perguntaRepository, Mockito.times(1)).save(pergunta);
    }

    @Test
    @DisplayName("Deve lançar exceção se tentar deletar uma pergunta com id <= 4")
    void deveLancarExcecaoParaIdsProtegidos() {
        Assertions.assertThrows(OperacaoNaoPermitidaException.class, () -> {
            perguntaService.deletarPergunta(3L);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção se tentar deletar pergunta inexistente")
    void deveLancarExcecaoParaPerguntaNaoEncontrada() {
        Long id = 10L;
        Mockito.when(perguntaRepository.existsById(id)).thenReturn(false);

        Assertions.assertThrows(RecursoNaoEcontradoException.class, () -> {
            perguntaService.deletarPergunta(id);
        });
    }

    @Test
    @DisplayName("Deve deletar pergunta com sucesso quando ID for válido e existir")
    void deveDeletarPerguntaComSucesso() {
        Long id = 10L;
        Mockito.when(perguntaRepository.existsById(id)).thenReturn(true);

        perguntaService.deletarPergunta(id);

        Mockito.verify(perguntaRepository, Mockito.times(1)).deleteById(id);
    }

}