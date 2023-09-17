package com.uade.api.controllers;

import com.uade.api.models.UsuarioModel;
import com.uade.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tpo_apis/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @PostMapping(path ="/")
    public ResponseEntity<?> createUnidad(@RequestBody UsuarioModel user) throws Exception {
        return new ResponseEntity<>(usuarioService.createUsuario(user), HttpStatus.CREATED);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateUnidad(@RequestBody UsuarioModel user, @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(usuarioService.updateUsuario(user, id), HttpStatus.OK);
    }
    @GetMapping(path ="/{id}")
    public ResponseEntity<?> getUnidadById(@PathVariable Long id) throws Exception {
        UsuarioModel user = usuarioService.findUsuarioById(id);

        if (user==null){
            String mensaje = "Unidad no encontrado con el Id: " + id;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping(path ="/")
    public List<UsuarioModel> getAllUnidades(){
        return usuarioService.findAllUsuarios();
    }
}
