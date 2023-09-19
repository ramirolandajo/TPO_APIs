package com.uade.api.services;

import com.uade.api.models.ReclamoModel;
import com.uade.api.repositories.IReclamoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class ReclamoService {

    @Autowired
    private IReclamoRepository reclamoRepository;

    public ReclamoModel createReclamo(ReclamoModel newReclamo) {
        return this.reclamoRepository.save(newReclamo);
    }

    public ReclamoModel updateReclamo(ReclamoModel reclamo, Long id) throws Exception {
        log.info("Id ingresado: " + id);
        if (id <= 0){
            log.info("El id ingresado no es valido.");
            throw new Exception("El id ingresado no es valido");
        }

        Optional<ReclamoModel> reclamoOp = reclamoRepository.findById(reclamo.getId_reclamo());

        if (reclamoOp.isEmpty()) {
            log.info("El reclamo que intenta actualizar no se encuentra en la base de datos");
            throw new Exception("El reclamo que intenta actualizar no se encuentra en la base de datos");
        }

        ReclamoModel reclamoDb = reclamoOp.get();
        reclamoDb.setDescripcion(reclamo.getDescripcion());
        reclamoDb.setUsuario(reclamo.getUsuario());
        reclamoDb.setEdificio(reclamo.getEdificio());
        reclamoDb.setEstado(reclamo.getEstado());
        reclamoDb.setUnidad(reclamo.getUnidad());

        log.info("El reclamo actualizado: " + reclamoDb);
        return this.reclamoRepository.save(reclamoDb);
    }

    public ReclamoModel findReclamoById(Long id) throws Exception {
        log.info("Id ingresado: " + id);
        if (id <= 0){
            log.info("El id ingresado no es valido.");
            throw new Exception("El id ingresado no es valido");
        }
        Optional<ReclamoModel> reclamoOp = reclamoRepository.findById(id);
        if (reclamoOp.isEmpty()) {
            throw new Exception("El reclamo de id: " + id + " no se encuentra en la BD");
        }
        return reclamoOp.get();
    }

    public List<ReclamoModel> findAllReclamos() {
        return this.reclamoRepository.findAll();
    }
}
