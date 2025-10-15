package com.example.desafio_sistema_cadastro.repository;

import com.example.desafio_sistema_cadastro.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByName(String name);
    List<Usuario> findByNameContainingIgnoreCase(String name);
    List<Usuario> findByEmailContainingIgnoreCase(String name);
    List<Usuario> findByIdade(Integer idade);
}
