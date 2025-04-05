package service;


import model.Usuario;
import utils.InputValidator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioService {
    String endereco = "C:\\Projects_java\\Desafio sistema de cadastros\\src\\data\\formulario.txt";
    List<Usuario> usuarios = new ArrayList<>();
    PerguntaService perguntaService = new PerguntaService();
    InputValidator inputValidator = new InputValidator();
    Scanner sc = new Scanner(System.in);

    public void cadastrarUsuario() {

        perguntaService.lerPerguntaEspecifica(1);
        String name = inputValidator.validarNome(sc);

        perguntaService.lerPerguntaEspecifica(2);
        String email = inputValidator.validarEmail(sc, usuarios);

        perguntaService.lerPerguntaEspecifica(3);
        int idade = inputValidator.validaIdade(sc);

        perguntaService.lerPerguntaEspecifica(4);
        String altura = inputValidator.validaAltura(sc);

        Usuario usuario = new Usuario(name, email, idade, altura);
        System.out.println("Cadastrado com sucesso!");
        System.out.println(usuario.toString());

        usuarios.add(usuario);

        String enderecoArquivo = "C:\\Projects_java\\Desafio sistema de cadastros\\" + usuarios.size() +
                " - " + usuario.getName().toUpperCase() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(enderecoArquivo))) {
            writer.write(usuario.getName());
            writer.newLine();
            writer.write(usuario.getEmail());
            writer.newLine();
            writer.write(String.valueOf(usuario.getIdade()));
            writer.newLine();
            writer.write(String.valueOf(usuario.getAltura()));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo" + e.getMessage());
        }

    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario encontrado!");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println(usuario.getName());
            }
        }
    }

    public void buscarUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome, idade ou email :");
        String entrada = sc.nextLine();

        boolean encontrado = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getName().toLowerCase().contains(entrada) ||
                    String.valueOf(usuario.getIdade()).contains(entrada) ||
                    usuario.getEmail().toLowerCase().contains(entrada)) {
                System.out.println("model.Usuario encontrado:\n" + usuario);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Usuário não encontrado.");
        }
    }
}