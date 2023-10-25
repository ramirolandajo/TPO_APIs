package com.uade.api.controllers;

import com.uade.api.models.UsuarioModel;
import com.uade.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tpo_apis/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @PostMapping(path ="/register")
    public ResponseEntity<?> registerUsuario(@RequestBody UsuarioModel user) throws Exception {
        return new ResponseEntity<>(usuarioService.createUsuario(user), HttpStatus.CREATED);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateUsuario(@RequestBody UsuarioModel user, @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(usuarioService.updateUsuario(user, id), HttpStatus.OK);
    }
    @GetMapping(path ="/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long id) throws Exception {
        UsuarioModel user = usuarioService.findUsuarioById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping(path ="/")
    public List<UsuarioModel> getAllUsuarios(){
        return usuarioService.findAllUsuarios();
    }
}
