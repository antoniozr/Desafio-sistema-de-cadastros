import service.PerguntaService;
import service.UsuarioService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UsuarioService gerenciadorDeUsuario = new UsuarioService();
        PerguntaService gerenciadorDePerguntas = new PerguntaService();
        gerenciadorDePerguntas.criarFile();

        Scanner sc = new Scanner(System.in);

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("======MENU======");
            System.out.println();
            System.out.println("1 - Cadastrar o usuário");
            System.out.println("2 - Listar todos usuários cadastrados");
            System.out.println("3 - Cadastrar nova pergunta no formulário");
            System.out.println("4 - Deletar pergunta do formulário");
            System.out.println("5 - Pesquisar usuário por nome ou idade ou email");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("### Cadastrar Usuário ###");
                    gerenciadorDeUsuario.cadastrarUsuario();
                    break;

                case 2:
                    System.out.println("### Listar Usuários ###");
                    gerenciadorDeUsuario.listarUsuarios();
                    break;

                case 3:
                    System.out.println("### Cadastrar Nova Pergunta ###");
                    gerenciadorDePerguntas.cadastrarNovaPergunta();
                    break;

                case 4:
                    System.out.println("### Deletar Pergunta ###");
                    gerenciadorDePerguntas.deletarPergunta();
                    break;

                case 5:
                    System.out.println("### Pesquisar Usuário ###");
                    gerenciadorDeUsuario.buscarUsuario();
                    break;

                case 0:
                    System.out.println("Sistema encerrado!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
    }



}