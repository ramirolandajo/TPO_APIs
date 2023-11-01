package com.uade.api.controllers;

import java.util.Date;

import javax.crypto.SecretKey;

import com.uade.api.models.DTOs.UsuarioModelDTO;
import com.uade.api.models.UsuarioModel;
import com.uade.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final int EXPIRATION_TIME_IN_MIN = 30;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SecretKey secretKey; // Inyecta la clave secreta

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioModelDTO credentials) {
        // Validar las credenciales aquí (puedes usar Spring Security u otros
        // mecanismos)
        UsuarioModel usuario = this.usuarioService.findUsuario(credentials.getUsuario(), credentials.getPassword());
        if (usuario != null) {
            // Crear el token JWT
            String token = Jwts.builder()
                    .subject(credentials.getUsuario()).issuedAt(new Date())
                    .claim("Rol", usuario.getTipoUsuario())
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MIN * 60 * 1000))
                    .signWith(secretKey, SignatureAlgorithm.HS256).compact();
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Credenciales inválidas.", HttpStatus.UNAUTHORIZED);
        }
    }

}
