package com.uade.api.services;

import com.uade.api.models.EdificioModel;
import com.uade.api.repositories.IEdificioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class EdificioService {

    @Autowired
    private IEdificioRepository edificioRepository;

    public EdificioModel createEdificio(EdificioModel newEdificio) throws Exception {
        Optional<EdificioModel> edificioOp = edificioRepository.findById(newEdificio.getIdEdificio());
        if (edificioOp.isPresent()) {
            throw new Exception("El edificio que esta intentando crear ya se encuentra en la base de datos");
        }
        return this.edificioRepository.save(newEdificio);
    }

    public EdificioModel updateEdificio(EdificioModel edificio, Long id) throws Exception {
        log.info("Id ingresado: " + id);
        if (id <= 0){
            log.info("El id ingresado no es valido.");
            throw new Exception("El id ingresado no es valido");
        }

        Optional<EdificioModel> edificioOp = edificioRepository.findById(edificio.getIdEdificio());

        if (edificioOp.isEmpty()) {
            log.info("El edificio que intenta actualizar no se encuentra en la base de datos");
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
            log.info("El id ingresado no es valido.");
            throw new Exception("El id ingresado no es valido");
        }
        Optional<EdificioModel> edificioOp = edificioRepository.findById(id);
        if (edificioOp.isEmpty()) {
            throw new Exception("El edificio de id: " + id + " no se encuentra en la BD");
        }
        return edificioOp.get();
    }

    public List<EdificioModel> findAllEdificios() {
        return this.edificioRepository.findAll();
    }
}
