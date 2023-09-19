package com.uade.api.controllers;

import com.uade.api.models.ReclamoModel;
import com.uade.api.services.ReclamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tpo_apis/reclamos")
public class ReclamoController {

    @Autowired
    private ReclamoService reclamoService;

    @PostMapping(path ="/")
    public ResponseEntity<?> createReclamo(@RequestBody ReclamoModel reclamo) {
        return new ResponseEntity<>(reclamoService.createReclamo(reclamo), HttpStatus.CREATED);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateReclamo(@RequestBody ReclamoModel reclamo, @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(reclamoService.updateReclamo(reclamo, id), HttpStatus.OK);
    }
    @GetMapping(path ="/{id}")
    public ResponseEntity<?> getReclamoById(@PathVariable Long id) throws Exception {
        ReclamoModel reclamo = reclamoService.findReclamoById(id);

        if (reclamo==null){
            String mensaje = "Reclamo no encontrado con el Id: " + id;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reclamo, HttpStatus.OK);
    }
    @GetMapping(path ="/")
    public List<ReclamoModel> getAllReclamos(){
        return reclamoService.findAllReclamos();
    }
}
