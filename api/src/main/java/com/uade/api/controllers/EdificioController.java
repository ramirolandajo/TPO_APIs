package com.uade.api.controllers;

import com.uade.api.models.EdificioModel;
import com.uade.api.services.EdificioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path ="tpo_apis/edificios")
public class EdificioController {

    @Autowired
    private EdificioService edificioService;

    @PostMapping(path ="/")
    public ResponseEntity<?> createEdificio(@RequestBody EdificioModel edificio) {
        return new ResponseEntity<>(edificioService.createEdificio(edificio), HttpStatus.CREATED);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateEdificio(@RequestBody EdificioModel edificio, @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(edificioService.updateEdificio(edificio, id), HttpStatus.OK);
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
