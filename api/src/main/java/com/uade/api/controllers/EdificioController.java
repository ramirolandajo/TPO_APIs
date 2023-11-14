package com.uade.api.controllers;

import com.uade.api.config.JwtAuthFilter;
import com.uade.api.models.EdificioModel;
import com.uade.api.models.TipoUsuario;
import com.uade.api.services.EdificioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.List;
@RestController
@RequestMapping(path ="tpo_apis/edificios")
public class EdificioController {

    @Autowired
    private EdificioService edificioService;

    @Autowired
    JwtAuthFilter jwtAuthFilter;

    @PostMapping(path ="/")
    public ResponseEntity<?> createEdificio(@RequestBody EdificioModel edificio, @RequestHeader(name = "Authorization") String token) {
        try {
            Claims claims = jwtAuthFilter.excractClaimsFromToken(token.substring(7));
            TipoUsuario tipoUsuario = TipoUsuario.convertStringToEnum(claims.get("rol"));
            if (!tipoUsuario.equals(TipoUsuario.ADMIN)) {
                return new ResponseEntity<>("Permisos invalidos para tipo de usuario: " + tipoUsuario, HttpStatus.UNAUTHORIZED);
            }
            else {
                return new ResponseEntity<>(edificioService.createEdificio(edificio), HttpStatus.CREATED);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateEdificio(@RequestBody EdificioModel edificio, @PathVariable Long id, @RequestHeader(name = "Authorization") String token) {
        try {
            Claims claims = jwtAuthFilter.excractClaimsFromToken(token.substring(7));
            TipoUsuario tipoUsuario = TipoUsuario.convertStringToEnum(claims.get("rol"));
            if (!tipoUsuario.equals(TipoUsuario.ADMIN)) {
                return new ResponseEntity<>("Permisos invalidos para tipo de usuario: " + tipoUsuario, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(edificioService.updateEdificio(edificio, id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping(path ="/{id}")
    public ResponseEntity<?> getEdificioById(@PathVariable Long id) throws Exception {
        EdificioModel edificio = edificioService.findEdificioById(id);

        if (edificio==null){
            String mensaje = "Edificio no encontrado con el Id: " + id;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(edificio, HttpStatus.OK);
    }
    @GetMapping(path ="/")
    public List<EdificioModel> getAllEdificios(){
        return edificioService.findAllEdificios();
    }
}
