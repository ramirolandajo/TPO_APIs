package com.uade.api.services;

import com.uade.api.models.EspacioComunModel;
import com.uade.api.repositories.IEspacioComunRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EspacioComunService {

    @Autowired
    private IEspacioComunRepository espacioComunRepository;

    public EspacioComunModel createEspacioComun(EspacioComunModel newEspacioComun) {
        return this.espacioComunRepository.save(newEspacioComun);
    }

    public EspacioComunModel updateEspacioComun(EspacioComunModel espacioComun, Long id) throws Exception {
        log.info("Id ingresado: " + id);
        if (id <= 0){
            log.info("El id ingresado no es valido.");
            throw new Exception("El id ingresado no es valido");
        }

        Optional<EspacioComunModel> espacioComunOp = this.espacioComunRepository.findById(espacioComun.getIdEspacioComun());

        if (espacioComunOp.isEmpty()) {
            log.info("El espacio comun que intenta actualizar no se encuentra en la base de datos");
            throw new Exception("El espacio comun que intenta actualizar no se encuentra en la base de datos");
        }

        EspacioComunModel espacioComunDb = espacioComunOp.get();
        espacioComunDb.setDescripcion(espacioComun.getDescripcion());
        espacioComunDb.setEdificio(espacioComun.getEdificio());
        espacioComunDb.setPiso(espacioComun.getPiso());

        log.info("El espacio comun actualizado: " + espacioComunDb);
        return this.espacioComunRepository.save(espacioComunDb);
    }

    public EspacioComunModel findEspacioComunById(Long id) throws Exception {
        log.info("Id ingresado: " + id);
        if (id <= 0){
            log.info("El id ingresado no es valido.");
            throw new Exception("El id ingresado no es valido");
        }
        Optional<EspacioComunModel> espacioComunOp = this.espacioComunRepository.findById(id);
        if (espacioComunOp.isEmpty()) {
            throw new Exception("El espacio comun de id: " + id + " no se encuentra en la BD");
        }
        return espacioComunOp.get();
    }

    public List<EspacioComunModel> findAllEspacioComunes() {
        return this.espacioComunRepository.findAll();
    }
}
