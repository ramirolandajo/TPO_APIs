package com.uade.api.controllers;

import com.uade.api.models.UsuarioModel;
import com.uade.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tpo_apis/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;
    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UsuarioModel usuario) throws Exception {
        usuarioService.createUsuario(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }
}
