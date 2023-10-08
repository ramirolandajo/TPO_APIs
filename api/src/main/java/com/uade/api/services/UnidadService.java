package com.uade.api.services;

import com.uade.api.models.EdificioModel;
import com.uade.api.models.UnidadModel;
import com.uade.api.models.UsuarioModel;
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
    @Autowired
    private UsuarioService usuarioService;

    public UnidadModel createUnidad(UnidadModel newUnidad, EdificioModel edificio) throws Exception {
        Optional<UsuarioModel> duenioOp = Optional.ofNullable(this.usuarioService.findUsuarioById(newUnidad.getDuenio().getIdUsuario()));
        if (duenioOp.isEmpty()) {
            log.error("El usuario (duenio) con ID " + newUnidad.getDuenio().getIdUsuario() + " no se encuentra en la BD");
            throw new Exception("El usuario (duenio) con ID " + newUnidad.getDuenio().getIdUsuario() + " no se encuentra en la BD");
        }
        if (newUnidad.getInquilino() != null) {
            Optional<UsuarioModel> inquilinoOp = Optional.ofNullable(this.usuarioService.findUsuarioById(newUnidad.getInquilino().getIdUsuario()));
            if (inquilinoOp.isEmpty()) {
                log.error("El usuario (inquilino) con ID " + newUnidad.getInquilino().getIdUsuario() + " no se encuentra en la BD");
                throw new Exception("El usuario (inquilino) con ID " + newUnidad.getInquilino().getIdUsuario() + " no se encuentra en la BD");
            }
        }
        newUnidad.setEdificio(edificio);
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
