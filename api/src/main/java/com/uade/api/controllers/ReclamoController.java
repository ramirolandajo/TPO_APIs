package com.uade.api.controllers;

import com.uade.api.models.DTOs.ReclamoModelDTO;
import com.uade.api.models.EspacioComunModel;
import com.uade.api.models.ReclamoModel;
import com.uade.api.services.*;
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
    public ResponseEntity<?> updateReclamo(@RequestBody ReclamoModelDTO reclamoDTO, @PathVariable Long id) {
        try {
            ReclamoModel reclamo = convertToEntity(reclamoDTO);
            return new ResponseEntity<>(reclamoService.updateReclamo(reclamo, id), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
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

    public ReclamoModel convertToEntity(ReclamoModelDTO reclamoDTO) throws Exception {
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
                    null
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
                    this.espacioComunService.findEspacioComunById(reclamoDTO.getIdEspacioComun())
            );
            return reclamo;
        }
    }

    public ReclamoModelDTO convertToDTO(ReclamoModel reclamo) {
        if (reclamo.getUnidad() != null) {
            ReclamoModelDTO reclamoDTO = new ReclamoModelDTO(
                    reclamo.getEstado(),
                    reclamo.getDescripcion(),
                    reclamo.getUsuario().getIdUsuario(),
                    reclamo.getEdificio().getIdEdificio(),
                    reclamo.getUnidad().getIdUnidad(),
                    null
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
                    reclamo.getEspacioComun().getIdEspacioComun()
            );
            return reclamoDTO;
        }
    }
}
