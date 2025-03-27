package service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorDePerguntas {
    String endereco = "C:\\Projects_java\\Desafio sistema de cadastros\\formulario.txt";


    public  void lerFormulario(){

        try (BufferedReader reader = new BufferedReader(new FileReader(endereco))){
            String linha;
            while ((linha = reader.readLine()) != null){
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Não foi possivel ler o arquivo: " + e.getMessage());
        }
    }

    public  void cadastrarNovaPergunta() {
        Scanner sc = new Scanner(System.in);

        //*verifica o numero da ultima pergunta no formulario
        int numeroQuestao = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(endereco))) {
            while ((reader.readLine() != null)) {
                numeroQuestao++;
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo" + e.getMessage());
        }

        System.out.println("Digite a nova pergunta: ");
        var novaPergunta = sc.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(endereco, true))) {
            writer.newLine();
            writer.write((numeroQuestao + 1) + " - " + novaPergunta);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ão adicionar a nova questão!" + e.getMessage());
        }

    }

    public void deletarPergunta() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Perguntas cadastradas.");
        lerFormulario();

        System.out.println("Digite o número da pergunta a ser deletada.");
        int perguntaDeletar = sc.nextInt();

        List<String> perguntas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(endereco))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                perguntas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo");
        }

        if (perguntaDeletar <= 4) {
            System.out.println("As perguntas de 1 a 4 não podem ser deletadas!");
        } else if (perguntaDeletar > perguntas.size()) {
            System.out.println("pergunta inexistente");
        } else {
            perguntas.remove(perguntaDeletar - 1);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(endereco))) {
                for (String pergunta : perguntas) {
                    writer.write(pergunta);
                    writer.newLine();
                }
                System.out.println("pergunta deletada com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ão deletar pergunta! ");
            }
        }

    }
}
