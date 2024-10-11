package com.uniara.service;

import com.uniara.model.Usuario;
import com.uniara.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    void testSalvarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Carlos");
        usuario.setSenha("senha123");

        when(usuarioRepository.save(usuario)).thenReturn(usuario); // Mock do repositório

        Usuario resultado = usuarioService.saveUsuario(usuario);

        assertNotNull(resultado);
        assertEquals("Carlos", resultado.getNome());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testFindUsuario_Sucesso() {
        Usuario usuario = new Usuario();
        usuario.setNome("Ana");
        usuario.setSenha("senha123");

        when(usuarioRepository.findByNomeAndSenha("ana", "senha123"))
                .thenReturn(Optional.of(usuario)); // Mock do repositório com nome minúsculo

        Optional<Usuario> resultado = usuarioService.findUsuario(usuario);

        assertTrue(resultado.isPresent());
        assertEquals("Ana", resultado.get().getNome());
        verify(usuarioRepository, times(1))
                .findByNomeAndSenha("ana", "senha123"); // Verifica que o método foi chamado corretamente
    }

    @Test
    void testFindUsuario_NaoEncontrado() {
        Usuario usuario = new Usuario();
        usuario.setNome("João");
        usuario.setSenha("senhaerrada");

        when(usuarioRepository.findByNomeAndSenha("joão", "senhaerrada"))
                .thenReturn(Optional.empty()); // Simula usuário não encontrado

        Optional<Usuario> resultado = usuarioService.findUsuario(usuario);

        assertFalse(resultado.isPresent());
        verify(usuarioRepository, times(1))
                .findByNomeAndSenha("joão", "senhaerrada"); // Verifica que o método foi chamado corretamente
    }
}
