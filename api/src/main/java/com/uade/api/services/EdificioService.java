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
        this.edificioRepository.save(newEdificio);          // guardamos el edificio en la bd para tener el id
        List<UnidadModel> unidades = new ArrayList<>();
        for (UnidadModel u: newEdificio.getUnidades()) {
            u.setEdificio(newEdificio);
            this.unidadService.createUnidad(u);               // creamos la unidad en la db para tener el id para el edificio
            unidades.add(u);
        }

        List<EspacioComunModel> espaciosComunes = new ArrayList<>();
        for (EspacioComunModel e: newEdificio.getEspaciosComunes()){
            e.setEdificio(newEdificio);
            this.espacioComunService.createEspacioComun(e);            // guardamos para tener el id para el edificio
            espaciosComunes.add(e);
        }

        newEdificio.setUnidades(unidades);
        newEdificio.setEspaciosComunes(espaciosComunes);
        log.info("Edificio completo: " + newEdificio);
        this.edificioRepository.save(newEdificio);          // actualizamos el edificio con las unidades y espacios comunes con sus ids
        return newEdificio;
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
