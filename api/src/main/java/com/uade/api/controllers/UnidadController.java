package com.uade.api.controllers;

import com.uade.api.models.DTOs.UnidadModelDTO;
import com.uade.api.models.ReclamoModel;
import com.uade.api.models.UnidadModel;
import com.uade.api.services.EdificioService;
import com.uade.api.services.UnidadService;
import com.uade.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "tpo_apis/unidades")
public class UnidadController {

    @Autowired
    private UnidadService unidadService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EdificioService edificioService;

    @PostMapping(path = "/")
    public ResponseEntity<?> createUnidad(@RequestBody UnidadModelDTO unidadDTO) {
        try {
            UnidadModel unidad = convertToEntity(unidadDTO);
            return new ResponseEntity<>(unidadService.createUnidad(unidad), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateUnidad(@RequestBody UnidadModelDTO unidadDTO, @PathVariable Long id) {
        try {
            UnidadModel unidad = convertToEntity(unidadDTO);
            return new ResponseEntity<>(unidadService.updateUnidad(unidad, id), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping(path ="/{id}")
    public ResponseEntity<?> getUnidadById(@PathVariable Long id) {
        try {
            UnidadModel unidad = unidadService.findUnidadById(id);
            UnidadModelDTO unidadDTO = convertToDTO(unidad);
            return new ResponseEntity<>(unidadDTO, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }
    @GetMapping(path ="/")
    public List<UnidadModelDTO> getAllUnidades(){
        List<UnidadModelDTO> unidadesDTO = new ArrayList<>();
        for (UnidadModel u: unidadService.findAllUnidades()) {
            unidadesDTO.add(convertToDTO(u));
        }
        return unidadesDTO;
    }

    private UnidadModelDTO convertToDTO(UnidadModel unidad) {
        UnidadModelDTO unidadModelDTO = new UnidadModelDTO(
                unidad.getPiso(),
                unidad.getNumero(),
                unidad.getDuenio().getIdUsuario(),
                unidad.getInquilino().getIdUsuario(),
                unidad.getEdificio().getIdEdificio()
        );
        return unidadModelDTO;
    }

    private UnidadModel convertToEntity(UnidadModelDTO unidadDTO) throws Exception {
        if (unidadDTO.getIdInquilino() != null) {
            UnidadModel unidad = new UnidadModel(
                    unidadDTO.getPiso(),
                    unidadDTO.getNumero(),
                    usuarioService.findUsuarioById(unidadDTO.getIdDuenio()),
                    usuarioService.findUsuarioById(unidadDTO.getIdInquilino()),
                    edificioService.findEdificioById(unidadDTO.getIdEdificio())
            );
            return unidad;
        } else {
            UnidadModel unidad = new UnidadModel(
                    unidadDTO.getPiso(),
                    unidadDTO.getNumero(),
                    usuarioService.findUsuarioById(unidadDTO.getIdDuenio()),
                    edificioService.findEdificioById(unidadDTO.getIdEdificio())
            );
            return unidad;
        }
    }
}
