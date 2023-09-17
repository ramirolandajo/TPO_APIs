package com.uade.api.controllers;

import com.uade.api.models.UnidadModel;
import com.uade.api.services.UnidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "/tpo_apis/unidades")
public class UnidadController {

    @Autowired
    private UnidadService unidadService;

    @PostMapping(path ="/")
    public ResponseEntity<?> createUnidad(@RequestBody UnidadModel unidad) throws Exception {
        return new ResponseEntity<>(unidadService.createUnidad(unidad), HttpStatus.CREATED);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateUnidad(@RequestBody UnidadModel unidad, @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(unidadService.updateUnidad(unidad, id), HttpStatus.OK);
    }
    @GetMapping(path ="/{id}")
    public ResponseEntity<?> getUnidadById(@PathVariable Long id) throws Exception {
        UnidadModel unidad = unidadService.findUnidadById(id);

        if (unidad==null){
            String mensaje = "Unidad no encontrado con el Id: " + id;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(unidad, HttpStatus.OK);
    }
    @GetMapping(path ="/")
    public List<UnidadModel> getAllUnidades(){
        return unidadService.findAllUnidades();
    }
}
