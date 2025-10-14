package com.example.desafio_sistema_cadastro.service;

import com.example.desafio_sistema_cadastro.dto.UsuarioDto;
import com.example.desafio_sistema_cadastro.model.Usuario;
import com.example.desafio_sistema_cadastro.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public List<UsuarioDto> listarNomesUsuarios() {
        return listarUsuarios()
                .stream()
                .map(u -> new UsuarioDto(u.getName()))
                .collect(Collectors.toList());
    }

    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

}
