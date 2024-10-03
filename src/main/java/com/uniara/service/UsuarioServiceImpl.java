package com.uniara.service;

import com.uniara.model.Usuario;
import com.uniara.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    @Override
    public Usuario saveUsuario(Usuario usuario) {
         return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findUsuario(Usuario usuario) {
        return usuarioRepository.findByNomeAndSenha(usuario.getNome().toLowerCase(),usuario.getSenha());
    }


}
