package com.example.desafio_sistema_cadastro.service;

import com.example.desafio_sistema_cadastro.exceptions.EmailDuplicadoException;
import com.example.desafio_sistema_cadastro.model.Usuario;
import com.example.desafio_sistema_cadastro.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve retornar uma lista com um usuário")
    void deveRetornarUmalistaComUmUsuario() {
        Usuario usuario = new Usuario(1L, "João Silva Queiroz", "Joao@mail.com", 18,"1,83");
        Mockito.when(usuarioRepository.findAll()).thenReturn(Collections.singletonList(usuario));
        List<Usuario> usuarios = usuarioService.listarUsuarios();

        Assertions.assertEquals(1,usuarios.size());
    }

    @Test
    @DisplayName("Deve Lancar Exceção Quando Email Duplicado")
    void deveLancarExcecaoQuandoEmailDuplicado() {
        Usuario usuario = new Usuario(1L, "João Silva Queiroz", "Joao@mail.com", 18,"1,83");

        Mockito.when(usuarioRepository.existsByEmail(usuario.getEmail()))
                .thenReturn(true);

        Assertions.assertThrows(EmailDuplicadoException.class, () -> {usuarioService.registrarUsuario(usuario);
        });
    }


    @Test
    @DisplayName("Deve registrar usuário com sucesso quando o email não existe")
    void deveRegistrarUsuarioComSucesso() {
        Usuario usuario = new Usuario(1L, "João Silva Queiroz", "Joao@mail.com", 18,"1,83");

        Mockito.when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(false);
        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.registrarUsuario(usuario);

        Assertions.assertEquals(usuario, resultado);

    }

    @Test
    @DisplayName("Deve buscar usuário pelo nome")
    void deveBuscarUsuarioPorNome() {
        Usuario usuario = new Usuario(1L, "João Silva Queiroz", "Joao@mail.com", 18,"1,83");
        Mockito.when(usuarioRepository.findByNameContainingIgnoreCase("João"))
                .thenReturn(Collections.singletonList(usuario));

        List<Usuario> resultado = usuarioService.buscar("João");

        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals("João Silva Queiroz", resultado.get(0).getName());
    }

    @Test
    @DisplayName("Deve buscar usuário pelo Email")
    void deveBuscarUsuarioPorEmail() {
        Usuario usuario = new Usuario(1L, "João Silva Queiroz", "Joao@mail.com", 18,"1,83");
        Mockito.when(usuarioRepository.findByEmailContainingIgnoreCase("Joao@mail.com"))
                .thenReturn(Collections.singletonList(usuario));

        List<Usuario> resultado = usuarioService.buscar("Joao@mail.com");

        Assertions.assertEquals("Joao@mail.com", resultado.get(0).getEmail());
    }

    @Test
    @DisplayName("Deve buscar usuário pela idade")
    void deveBuscarUsuarioPorIdade() {
        Usuario usuario = new Usuario(1L, "João Silva Queiroz", "Joao@mail.com", 18,"1,83");
        Mockito.when(usuarioRepository.findByIdade(18))
                .thenReturn(Collections.singletonList(usuario));

        List<Usuario> resultado = usuarioService.buscar("18");

        Assertions.assertEquals(18, resultado.get(0).getIdade());
    }

}