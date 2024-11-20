import exceptions.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String endereco = "C:\\Projects_java\\Desafio sistema de cadastros\\formulario.txt";
        List<Usuario> usuarios = new ArrayList<>();
        menu(endereco, usuarios);
    }

    public static void menu(String endereco, List<Usuario> usuarios) {
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

            switch (opcao) {
                case 1:
                    lerQuestoes(endereco);
                    cadastrarUsuario(usuarios);
                    break;
                case 2:
                    listarUsuarios(usuarios);
                    break;
                case 3:
                    cadastrarNovaPergunta(endereco);
                    break;
                case 4:
                    deleterPergunta(endereco);
                    break;
                case 5:
                        buscarUsuario(usuarios);
                break;
                case 0:
                    System.out.println("Programa encerrado!");
            }
        }
    }

    public static void lerQuestoes(String endereco){

        try (BufferedReader reader = new BufferedReader(new FileReader(endereco))){
            String linha;
            while ((linha = reader.readLine()) != null){
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Não foi possivel ler o arquivo: " + e.getMessage());
        }
    }

    public static void cadastrarUsuario(List<Usuario> usuarios) {

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


    public static void listarUsuarios(List<Usuario> usuarios) {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario encontrado!");
        }else {
            for (Usuario usuario : usuarios){
                System.out.println(usuario.getName());
            }
        }
    }

    public static void cadastrarNovaPergunta(String endereco) {
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

    public static void deleterPergunta(String endereco) {
        Scanner sc = new Scanner(System.in);

        System.out.println("----- Perguntas cadastradas -----");
        lerQuestoes(endereco);
        System.out.println("\nDigite o número da pergunta que você deseja deletar!");
        int pergutaDeletada = sc.nextInt();

        List<String> perguntas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(endereco))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                perguntas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo" + e.getMessage());
        }

        if (pergutaDeletada <= 4){
            System.out.println("As perguntas de 1 a 4 não podem ser deletadas!");
        } else if ( pergutaDeletada > perguntas.size()){
            System.out.println("Pergunta inexistente!");
        } else {
            perguntas.remove(pergutaDeletada - 1);
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(endereco))){
                for (String pergunta: perguntas) {
                    writer.write(pergunta);
                    writer.newLine();
                }
                System.out.println("Pergunta deletada com sucesso!");
            } catch (IOException e){
                System.out.println("Erro ao deletar pergunta!" + e.getMessage());
            }
        }
    }

    public static void buscarUsuario(List<Usuario> usuarios){
        Scanner sc =new Scanner(System.in);
        System.out.println("Digite o nome, idade ou email :");
        String entrada = sc.nextLine();

        boolean encontrado = false;
        for (Usuario usuario : usuarios){
            if (usuario.getName().toLowerCase().contains(entrada) ||
                    String.valueOf(usuario.getIdade()).contains(entrada) ||
                    usuario.getEmail().toLowerCase().contains(entrada)) {
                System.out.println("Usuario encontrado:\n" + usuario);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Usuário não encontrado.");
        }

    }
    

}