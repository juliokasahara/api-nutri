package com.uniara.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException() {
        super();
    }
}
