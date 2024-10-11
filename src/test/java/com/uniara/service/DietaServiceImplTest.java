package com.uniara.service;

import com.uniara.dto.DietaDTO;
import com.uniara.model.Dieta;
import com.uniara.model.Usuario;
import com.uniara.repository.DietaRepository;
import com.uniara.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DietaServiceImplTest {

    @InjectMocks
    private DietaServiceImpl dietaService;

    @Mock
    private DietaRepository dietaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvarDieta() {
        Dieta dieta = new Dieta();
        dieta.setNome("Dieta Teste");
        dieta.setDescricao("Descrição da dieta teste");
        dieta.setCaloria(new BigDecimal("500"));

        when(dietaRepository.save(dieta)).thenReturn(dieta);

        Dieta resultado = dietaService.save(dieta);

        assertNotNull(resultado);
        assertEquals("Dieta Teste", resultado.getNome());
        verify(dietaRepository, times(1)).save(dieta);
    }

    @Test
    void testListarDietasDeUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        Dieta dieta1 = new Dieta("Dieta 1", "Descrição 1", new BigDecimal("300"));
        Dieta dieta2 = new Dieta("Dieta 2", "Descrição 2", new BigDecimal("400"));
        usuario.setDietas(new HashSet<>(Arrays.asList(dieta1, dieta2)));

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        List<Dieta> resultado = dietaService.listarDietasDeUsuario(1L);

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(dieta1));
        assertTrue(resultado.contains(dieta2));
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void testCadastrarDietaUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        DietaDTO dietaDTO = new DietaDTO();
        dietaDTO.setNome("Nova Dieta");
        dietaDTO.setDescricao("Descrição da nova dieta");
        dietaDTO.setCaloria(new BigDecimal("500"));

        Dieta novaDieta = new Dieta("Nova Dieta", "Descrição da nova dieta", new BigDecimal("500"));
        when(dietaRepository.save(any(Dieta.class))).thenReturn(novaDieta);

        dietaService.cadastrarDietaUsuario(1L, dietaDTO);

        verify(dietaRepository, times(1)).save(any(Dieta.class));
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testEditarDietaUsuario() {
        Dieta dieta = new Dieta();
        dieta.setId(1L);
        dieta.setNome("Dieta Antiga");
        dieta.setDescricao("Descrição antiga");
        dieta.setCaloria(new BigDecimal("400"));

        DietaDTO dietaDTO = new DietaDTO();
        dietaDTO.setId(1L);
        dietaDTO.setNome("Dieta Atualizada");
        dietaDTO.setDescricao("Descrição atualizada");
        dietaDTO.setCaloria(new BigDecimal("600"));

        when(dietaRepository.findById(1L)).thenReturn(Optional.of(dieta));
        when(dietaRepository.save(dieta)).thenReturn(dieta);

        Dieta resultado = dietaService.editarDietaUsuario(dietaDTO);

        assertNotNull(resultado);
        assertEquals("Dieta Atualizada", resultado.getNome());
        assertEquals(new BigDecimal("600"), resultado.getCaloria());
        verify(dietaRepository, times(1)).findById(1L);
        verify(dietaRepository, times(1)).save(dieta);
    }

    @Test
    void testUsuarioNaoEncontradoException() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            dietaService.listarDietasDeUsuario(1L);
        });

        assertEquals("Usuário não encontrado", exception.getMessage());
    }
}
