package service;

import exceptions.*;
import model.Usuario;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioService {
    String endereco = "C:\\Projects_java\\Desafio sistema de cadastros\\formulario.txt";
    List<Usuario> usuarios = new ArrayList<>();


    public void cadastrarUsuario() {

        Scanner sc = new Scanner(System.in);

        try {
            String name = sc.nextLine();
            if (name.length() < 10) {
                throw new InvalidNomeException("O nome deve possuir no minimo 10 caracteres!");
            }

            String email = sc.nextLine();
            if (!email.contains("@")) {
                throw new IvalidEmailException("O email deve conter @");
            }

            for (Usuario usuario: usuarios){
                if (usuario.getEmail().equals(email)){
                    throw new EmailDuplicadoException("Este email já está cadastrado!");
                }
            }

            int idade = sc.nextInt();
            if (idade<18) {
                throw new InvalidIdadeExcpetion("Idade minima 18 anos.");
            }

            sc.nextLine();

            String altura = sc.nextLine();
            if (!altura.contains(",")) {
                throw new InvalidAlturaException("Altura inválida, use vírgula (ex: 1,80).");
            }


            Usuario usuario = new Usuario(name, email, idade, altura);

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
        }catch (InvalidNomeException | IvalidEmailException | EmailDuplicadoException |
                InvalidIdadeExcpetion | InvalidAlturaException  e){
            System.out.println(e.getMessage());
        }
    }


    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario encontrado!");
        }else {
            for (Usuario usuario : usuarios){
                System.out.println(usuario.getName());
            }
        }
    }

    public void buscarUsuario(){
        Scanner sc =new Scanner(System.in);
        System.out.println("Digite o nome, idade ou email :");
        String entrada = sc.nextLine();

        boolean encontrado = false;
        for (Usuario usuario : usuarios){
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
