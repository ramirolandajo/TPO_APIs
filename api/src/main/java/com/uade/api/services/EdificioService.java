package com.uade.api.services;

import com.uade.api.models.EdificioModel;
import com.uade.api.models.EspacioComunModel;
import com.uade.api.models.UnidadModel;
import com.uade.api.repositories.IEdificioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class EdificioService {
    @Autowired
    private IEdificioRepository edificioRepository;
    @Autowired
    private EspacioComunService espacioComunService;
    @Autowired
    private UnidadService unidadService;

    public EdificioModel createEdificio(EdificioModel newEdificio) throws Exception {
        // verificamos que tenga la direccion
        if (newEdificio.getDireccion()==null){
            log.error("El edificio debe tener una dirección.");
            throw new Exception("El edificio debe tener una dirección.");
        }

        // verificamos que no tengan ninguna unidad ni espacio comun ya que se agregan posteriormente
        if (newEdificio.getUnidades()!=null){
            log.error("El edificio no debe tener unidades asociadas.");
            throw new Exception("El edificio no debe tener unidades asociadas.");
        }
        if (newEdificio.getEspaciosComunes()!=null){
            log.error("El edificio no debe tener espacios comunes asociados.");
            throw new Exception("El edificio no debe tener espacios comunes asociados.");
        }

        log.info("Edificio cargado: " + newEdificio);
        return this.edificioRepository.save(newEdificio);
    }

    public EdificioModel updateEdificio(EdificioModel edificio, Long id) throws Exception {
        log.info("Id ingresado: " + id);
        if (id <= 0){
            log.error("El id ingresado no es valido.");
            throw new Exception("El id ingresado no es valido");
        }

        Optional<EdificioModel> edificioOp = this.edificioRepository.findById(edificio.getIdEdificio());

        if (edificioOp.isEmpty()) {
            log.error("El edificio que intenta actualizar no se encuentra en la base de datos");
            throw new Exception("El edificio que intenta actualizar no se encuentra en la base de datos");
        }

        EdificioModel edificioDb = edificioOp.get();
        edificioDb.setDireccion(edificio.getDireccion());
        edificioDb.setUnidades(edificio.getUnidades());
        edificioDb.setEspaciosComunes(edificio.getEspaciosComunes());

        log.info("El edificio actualizado: " + edificioDb);
        return this.edificioRepository.save(edificioDb);
    }

    public EdificioModel findEdificioById(Long id) throws Exception {
        log.info("Id ingresado: " + id);
        if (id <= 0){
            log.error("El id ingresado no es valido.");
            throw new Exception("El id ingresado no es valido");
        }
        Optional<EdificioModel> edificioOp = this.edificioRepository.findById(id);
        if (edificioOp.isEmpty()) {
            throw new Exception("El edificio de id: " + id + " no se encuentra en la BD");
        }
        return edificioOp.get();
    }

    public List<EdificioModel> findAllEdificios() {
        return this.edificioRepository.findAll();
    }
}
