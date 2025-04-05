package utils;

import java.util.List;
import java.util.Scanner;

import model.Usuario;

public class InputValidator {

    public String validarNome(Scanner sc) {
        String nomeFornecido = sc.nextLine();
        try {
            if (nomeFornecido.length() < 10) {
                throw new IllegalArgumentException("O nome deve possuir no minimo 10 caracteres.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage() + " Tente novamente");
            return validarNome(sc);
        }
        return nomeFornecido;
    }

    public String validarEmail(Scanner sc, List<Usuario> usuarios) {
        String emailRecebido = sc.nextLine();
        try {
            if (!emailRecebido.contains("@")) {
                throw new IllegalArgumentException("O email deve conter @");
            }
            for (Usuario usuario : usuarios) {
                if (usuario.getEmail().equals(emailRecebido)) {
                    throw new IllegalArgumentException("Este email já está cadastrado!");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage() + " Tente novamente");
            return validarEmail(sc, usuarios);
        }
        return emailRecebido;
    }

    public int validaIdade(Scanner sc) {
        int idadeRecebida = sc.nextInt();
        sc.nextLine();
        try {
            if (idadeRecebida < 18) {
                throw new IllegalArgumentException("Idade minima 18 anos.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage() + " Tente novamente");
            return validaIdade(sc);
        }
        return idadeRecebida;
    }

    public String validaAltura(Scanner sc) {
        String alturaRecebida = sc.nextLine();
        try {
            if (!alturaRecebida.contains(",")) {
                throw new IllegalArgumentException("Altura inválida, use vírgula (ex: 1,80).");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage() + " Tente novamente");
            return validaAltura(sc);
        }
        return alturaRecebida;
    }
}
