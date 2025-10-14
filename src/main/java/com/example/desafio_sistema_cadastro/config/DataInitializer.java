package com.example.desafio_sistema_cadastro.config;

import com.example.desafio_sistema_cadastro.model.Pergunta;
import com.example.desafio_sistema_cadastro.repository.PerguntaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PerguntaRepository perguntaRepository;

    public DataInitializer(PerguntaRepository perguntaRepository) {
        this.perguntaRepository = perguntaRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (perguntaRepository.count() == 0){
            List<Pergunta> perguntasIniciais = List.of(
                    new Pergunta("Qual seu nome completo?"),
                    new Pergunta("Qual seu email de contato?"),
                    new Pergunta("Qual sua idade?"),
                    new Pergunta("Qual sua altura?")
            );


            perguntaRepository.saveAll(perguntasIniciais);
            System.out.printf("Perguntas iniciais criadas com sucesso!");
        } else {
            System.out.printf("Perguntas já existentes, nenhuma inserção feita");
        }
    }
}
