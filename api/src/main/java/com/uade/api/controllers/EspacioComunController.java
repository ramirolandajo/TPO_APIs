package com.uade.api.controllers;

import com.uade.api.models.DTOs.EspacioComunDTO;
import com.uade.api.models.EspacioComunModel;
import com.uade.api.models.EspacioComunModel;
import com.uade.api.services.EdificioService;
import com.uade.api.services.EspacioComunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "tpo_apis/espacios_comunes")
public class EspacioComunController {
    @Autowired
    private EspacioComunService espacioComunService;
    @Autowired
    private EdificioService edificioService;

    @PostMapping(path ="/")
    public ResponseEntity<?> createEspacioComun(@RequestBody EspacioComunDTO espacioComunDTO) {
        try {
            EspacioComunModel espacioComun = convertToEntity(espacioComunDTO);
            return new ResponseEntity<>(espacioComunService.createEspacioComun(espacioComun), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateEspacioComun(@RequestBody EspacioComunDTO espacioComunDTO, @PathVariable Long id) {
        try {
            EspacioComunModel espacioComun = convertToEntity(espacioComunDTO);
            return new ResponseEntity<>(espacioComunService.updateEspacioComun(espacioComun, id), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping(path ="/{id}")
    public ResponseEntity<?> getEspacioComunById(@PathVariable Long id) throws Exception {
        EspacioComunModel espacioComun = espacioComunService.findEspacioComunById(id);

        if (espacioComun==null){
            String mensaje = "EspacioComun no encontrado con el Id: " + id;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(espacioComun, HttpStatus.OK);
    }
    @GetMapping(path ="/")
    public List<EspacioComunModel> getAllEspacioComunes(){
        return espacioComunService.findAllEspacioComunes();
    }

    private EspacioComunDTO convertToDTO(EspacioComunModel espacioComun) {
        EspacioComunDTO espacioComunModelDTO = new EspacioComunDTO(
                espacioComun.getPiso(),
                espacioComun.getDescripcion(),
                espacioComun.getEdificio().getIdEdificio()
        );
        return espacioComunModelDTO;
    }

    private EspacioComunModel convertToEntity(EspacioComunDTO espacioComunDTO) throws Exception {
        EspacioComunModel espacioComun = new EspacioComunModel(
                espacioComunDTO.getPiso(),
                espacioComunDTO.getDescripcion(),
                this.edificioService.findEdificioById(espacioComunDTO.getIdEdificio())
        );
        return espacioComun;
    }
}
