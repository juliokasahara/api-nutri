package com.uniara.controller;

import com.uniara.exception.UsuarioNaoEncontradoException;
import com.uniara.model.Usuario;
import com.uniara.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthUsuarioController {

    private final UsuarioService usuarioService;
    @PostMapping
    private Usuario auth(@RequestBody Usuario usuario){
        return usuarioService.findUsuario(usuario).map(ResponseEntity::ok)
                .orElseThrow(UsuarioNaoEncontradoException::new).getBody();
    }
}
