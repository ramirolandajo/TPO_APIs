package com.uade.api.services;

import com.uade.api.models.UnidadModel;
import com.uade.api.repositories.IUnidadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class UnidadService {
    @Autowired
    private IUnidadRepository unidadRepository;

    public UnidadModel createUnidad(UnidadModel newUnidad) throws Exception {
        Optional<UnidadModel> unidadOp = unidadRepository.findById(newUnidad.getIdUnidad());
        if (unidadOp.isPresent()) {
            throw new Exception("La unidad que esta intentando crear ya se encuentra en la base de datos");
        }
        return this.unidadRepository.save(newUnidad);
    }

    public UnidadModel updateUnidad(UnidadModel unidad, Long id) throws Exception {
        log.info("Id ingresado: " + id);
        if (id <= 0){
            log.info("El id ingresado no es valido.");
            throw new Exception("El id ingresado no es valido");
        }

        Optional<UnidadModel> unidadOp = unidadRepository.findById(unidad.getIdUnidad());

        if (unidadOp.isEmpty()) {
            log.info("La unidad que intenta actualizar no se encuentra en la base de datos");
            throw new Exception("La unidad que intenta actualizar no se encuentra en la base de datos");
        }

        UnidadModel unidadDb = unidadOp.get();
        unidadDb.setEdificio(unidad.getEdificio());
        unidadDb.setHabitada(unidad.isHabitada());
        unidadDb.setDuenio(unidad.getDuenio());
        unidadDb.setNumero(unidad.getNumero());
        unidadDb.setPiso(unidad.getPiso());
        unidadDb.setInquilino(unidad.getInquilino());

        log.info("El unidad actualizado: " + unidadDb);
        return this.unidadRepository.save(unidadDb);
    }

    public UnidadModel findUnidadById(Long id) throws Exception {
        log.info("Id ingresado: " + id);
        if (id <= 0){
            log.info("El id ingresado no es valido.");
            throw new Exception("El id ingresado no es valido");
        }
        Optional<UnidadModel> unidadOp = unidadRepository.findById(id);
        if (unidadOp.isEmpty()) {
            throw new Exception("La unidad de id: " + id + " no se encuentra en la BD");
        }
        return unidadOp.get();
    }

    public List<UnidadModel> findAllUnidades() {
        return this.unidadRepository.findAll();
    }
}
