package com.uniara.service;

import com.uniara.model.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Usuario saveUsuario(Usuario usuario);
    Optional<Usuario> findUsuario(Usuario usuario);;

}
