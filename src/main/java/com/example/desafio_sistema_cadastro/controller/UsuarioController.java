package com.example.desafio_sistema_cadastro.controller;

import com.example.desafio_sistema_cadastro.dto.UsuarioDto;
import com.example.desafio_sistema_cadastro.model.Usuario;
import com.example.desafio_sistema_cadastro.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioDto> listarUsuarios() {
        return usuarioService.listarNomesUsuarios();
    }

    @PostMapping
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
    }
}
