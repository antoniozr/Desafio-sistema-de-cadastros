package com.example.desafio_sistema_cadastro.controller;

import com.example.desafio_sistema_cadastro.model.Pergunta;
import com.example.desafio_sistema_cadastro.service.PerguntaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/perguntas")
public class PerguntaController {

    private final PerguntaService perguntaService;

    public PerguntaController(PerguntaService perguntaService) {
        this.perguntaService = perguntaService;
    }

    @GetMapping
    public List<Pergunta> listarPerguntas() {
        return perguntaService.listarPerguntas();
    }

    @PostMapping
    public Pergunta cadastrarPergunta(@RequestBody Pergunta pergunta) {
        return perguntaService.registrarPergunta(pergunta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPergunta(@PathVariable Long id) {
        perguntaService.deletarPergunta(id);
        return ResponseEntity.noContent().build();
    }
}
