package com.example.desafio_sistema_cadastro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatorio")
    @Size(min = 10, message = "Nome deve ter pelo menos 10 caracteres")
    private String name;

    @NotBlank(message = "Email é obrigatorio")
    @Email(message = "Email invalido")
    private String email;

    @Min(value = 18, message = "Usuário deve ter pelo menos 18 anos")
    private int idade;

    @Pattern(regexp = "^\\d+(,\\d+)?$", message = "Altura deve ser um número com virgula (ex: 1,75")
    private String altura;

    public Usuario() {
    }

    public Usuario(Long id, String name, String email, int idade, String altura) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.idade = idade;
        this.altura = altura;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }
}
