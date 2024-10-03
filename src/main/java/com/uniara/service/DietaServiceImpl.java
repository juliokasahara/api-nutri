package com.uniara.service;

import com.uniara.dto.DietaDTO;
import com.uniara.model.Dieta;
import com.uniara.model.Usuario;
import com.uniara.repository.DietaRepository;
import com.uniara.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DietaServiceImpl implements DietaService {

    private final DietaRepository dietaRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public Dieta save(Dieta dieta) {
        return dietaRepository.save(dieta);
    }

    @Override
    public List<Dieta> listarDietasDeUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new ArrayList<>(usuario.getDietas());
    }

    @Override
    public void cadastrarDietaUsuario(Long idUsuario, DietaDTO dietaDTO) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Dieta novaDieta = new Dieta();
        novaDieta.setNome(dietaDTO.getNome());
        novaDieta.setDescricao(dietaDTO.getDescricao());
        novaDieta.setCaloria(dietaDTO.getCaloria());

        dietaRepository.save(novaDieta);

        usuario.getDietas().add(novaDieta);
        usuarioRepository.save(usuario);

    }

}
