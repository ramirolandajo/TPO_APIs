package com.uade.api.controllers;

import com.uade.api.models.DTOs.ReclamoDevueltoDTO;
import com.uade.api.models.DTOs.ReclamoModelDTO;
import com.uade.api.models.EspacioComunModel;
import com.uade.api.models.ReclamoModel;
import com.uade.api.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "tpo_apis/reclamos")
public class ReclamoController {

    @Autowired
    private ReclamoService reclamoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EdificioService edificioService;

    @Autowired
    private UnidadService unidadService;

    @Autowired
    private EspacioComunService espacioComunService;

    @PostMapping(path ="/")
    public ResponseEntity<?> createReclamo(@RequestBody ReclamoModelDTO reclamoDTO) {
        try {
            ReclamoModel reclamo = convertToEntity(reclamoDTO);
            return new ResponseEntity<>(reclamoService.createReclamo(reclamo), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateEstadoReclamo(@RequestBody String estadoReclamo, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(reclamoService.updateEstadoReclamo(estadoReclamo, id), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteReclamo(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(reclamoService.deleteReclamo(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<?> getReclamoById(@PathVariable Long id) {

        try {
            ReclamoModel reclamo = reclamoService.findReclamoById(id);
            return new ResponseEntity<>(reclamo, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(path ="/all")
    public ResponseEntity<List<ReclamoDevueltoDTO>> getAllReclamos(){
        List<ReclamoModel> reclamos = reclamoService.findAllReclamos();
        List<ReclamoDevueltoDTO> reclamosDTO = new ArrayList<>();
        for (ReclamoModel reclamo : reclamos) {
            reclamosDTO.add(converToDTO(reclamo));
        }
        return new ResponseEntity<>(reclamosDTO, HttpStatus.OK);    }

    @GetMapping(path = "/allFromUser/{id}")
    public ResponseEntity<List<ReclamoDevueltoDTO>> getAllReclamosFromUser(@PathVariable Long id) {
        List<ReclamoModel> reclamosFromUser = reclamoService.findAllReclamosFromUser(id);
        List<ReclamoDevueltoDTO> reclamosFromUserDTO = new ArrayList<>();
        for (ReclamoModel reclamo : reclamosFromUser) {
            reclamosFromUserDTO.add(converToDTO(reclamo));
        }
        return new ResponseEntity<>(reclamosFromUserDTO, HttpStatus.OK);
    }


    private ReclamoModel convertToEntity(ReclamoModelDTO reclamoDTO) throws Exception {
        if (reclamoDTO.getIdUnidad() != null && reclamoDTO.getIdEspacioComun() != null) {
            throw new Exception("No se puede crear un reclamo con unidad y espacio comun");
        }
        else if (reclamoDTO.getIdUnidad() != null) {
            ReclamoModel reclamo = new ReclamoModel(
                    reclamoDTO.getEstado(),
                    reclamoDTO.getDescripcion(),
                    this.usuarioService.findUsuarioById(reclamoDTO.getIdUsuario()),
                    this.edificioService.findEdificioById(reclamoDTO.getIdEdificio()),
                    this.unidadService.findUnidadById(reclamoDTO.getIdUnidad()),
                    null,
                    reclamoDTO.getImagen()
            );
            return reclamo;
        }
        else {
            ReclamoModel reclamo = new ReclamoModel(
                    reclamoDTO.getEstado(),
                    reclamoDTO.getDescripcion(),
                    this.usuarioService.findUsuarioById(reclamoDTO.getIdUsuario()),
                    this.edificioService.findEdificioById(reclamoDTO.getIdEdificio()),
                    null,
                    this.espacioComunService.findEspacioComunById(reclamoDTO.getIdEspacioComun()),
                    reclamoDTO.getImagen()
            );
            return reclamo;
        }
    }

    private ReclamoModelDTO convertToDTO(ReclamoModel reclamo) {
        if (reclamo.getUnidad() != null) {
            ReclamoModelDTO reclamoDTO = new ReclamoModelDTO(
                    reclamo.getEstado(),
                    reclamo.getDescripcion(),
                    reclamo.getUsuario().getIdUsuario(),
                    reclamo.getEdificio().getIdEdificio(),
                    reclamo.getUnidad().getIdUnidad(),
                    null,
                    reclamo.getImagen()
            );
            return reclamoDTO;
        }
        else {
            ReclamoModelDTO reclamoDTO = new ReclamoModelDTO(
                    reclamo.getEstado(),
                    reclamo.getDescripcion(),
                    reclamo.getUsuario().getIdUsuario(),
                    reclamo.getEdificio().getIdEdificio(),
                    null,
                    reclamo.getEspacioComun().getIdEspacioComun(),
                    reclamo.getImagen()
            );
            return reclamoDTO;
        }
    }

    private ReclamoDevueltoDTO converToDTO(ReclamoModel reclamo) {
        if (reclamo.getUnidad() != null) {
            ReclamoDevueltoDTO reclamoDTO = new ReclamoDevueltoDTO(
                    reclamo.getIdReclamo(),
                    reclamo.getEstado(),
                    reclamo.getDescripcion(),
                    reclamo.getEdificio().getDireccion(),
                    "Unidad: " + reclamo.getUnidad().getIdUnidad(),
                    reclamo.getImagen()
            );
            return reclamoDTO;
        }
        else {
            ReclamoDevueltoDTO reclamoDTO = new ReclamoDevueltoDTO(
                    reclamo.getIdReclamo(),
                    reclamo.getEstado(),
                    reclamo.getDescripcion(),
                    reclamo.getEdificio().getDireccion(),
                    "Espacio Comun: " + reclamo.getEspacioComun().getDescripcion(),
                    reclamo.getImagen()
            );
            return reclamoDTO;
        }
    }
}
