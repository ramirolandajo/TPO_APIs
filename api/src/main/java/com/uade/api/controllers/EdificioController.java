package com.uade.api.controllers;

import com.uade.api.models.EdificioModel;
import com.uade.api.services.EdificioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin()
@RestController
@RequestMapping(path ="/tpo_apis/edificios")
public class EdificioController {

    @Autowired
    private EdificioService edificioService;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path ="/")
    public ResponseEntity<?> createEdificio(@RequestBody EdificioModel edificio) {
        try {
            return new ResponseEntity<>(edificioService.createEdificio(edificio), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateEdificio(@RequestBody EdificioModel edificio, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(edificioService.updateEdificio(edificio, id), HttpStatus.OK);
        }
        catch (Exception e) {
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
    public ResponseEntity<List<EdificioModel>> getAllEdificios(){
        return new ResponseEntity<>(edificioService.findAllEdificios(), HttpStatus.OK);
    }
}
