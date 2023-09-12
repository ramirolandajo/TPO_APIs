package com.uade.api.controllers;

import com.uade.api.models.EdificioModel;
import com.uade.api.services.IEdificioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TPO_APIs")
public class EdificioController {

    @Autowired
    private IEdificioService edificioService;

    @GetMapping({"edificios",""})
    public List<EdificioModel> findAll(){
        return edificioService.findAll();
    }
    @GetMapping("edificios/idEdificio")
    public ResponseEntity<?> getEdificio(@PathVariable int idEdificio){
        EdificioModel edificio = edificioService.findById(idEdificio);

        if (edificio==null){
            String mensaje = "Edificio no encontrado con el Id: " + idEdificio;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(edificio, HttpStatus.OK);
    }

    @PostMapping("edificios")
    public ResponseEntity<?> addEdificio(@RequestBody EdificioModel edificio){
        edificioService.save(edificio);
        return new ResponseEntity<>(edificio, HttpStatus.CREATED);
    }
}
