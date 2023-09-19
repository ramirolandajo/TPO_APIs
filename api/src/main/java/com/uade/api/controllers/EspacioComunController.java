package com.uade.api.controllers;

import com.uade.api.models.EspacioComunModel;
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

    @PostMapping(path ="/")
    public ResponseEntity<?> createEspacioComun(@RequestBody EspacioComunModel espacioComun) {
        return new ResponseEntity<>(espacioComunService.createEspacioComun(espacioComun), HttpStatus.CREATED);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateEspacioComun(@RequestBody EspacioComunModel espacioComun, @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(espacioComunService.updateEspacioComun(espacioComun, id), HttpStatus.OK);
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
}
