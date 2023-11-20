package com.uade.api.controllers;

import com.uade.api.models.UsuarioModel;
import com.uade.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("tpo_apis/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @PostMapping(path ="/signUp")
    public ResponseEntity<?> registerUsuario(@RequestBody UsuarioModel user){
        try {
            return new ResponseEntity<>(usuarioService.createUsuario(user), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateUsuario(@RequestBody UsuarioModel user, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(usuarioService.updateUsuario(user, id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping(path ="/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long id) {
        try {
            UsuarioModel user = usuarioService.findUsuarioById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping(path ="/")
    public ResponseEntity<List<UsuarioModel>> getAllUsuarios(){
        return new ResponseEntity<>(usuarioService.findAllUsuarios(), HttpStatus.OK);
    }
}
