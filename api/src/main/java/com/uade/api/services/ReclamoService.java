package com.uade.api.services;

import com.uade.api.models.*;
import com.uade.api.repositories.IReclamoRepository;
import com.uade.api.repositories.IUsuarioRepository;
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
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UnidadService unidadService;
    @Autowired
    private EdificioService edificioService;
    @Autowired
    private EspacioComunService espacioComunService;

    public ReclamoModel createReclamo(ReclamoModel newReclamo) throws Exception {
        Optional<UsuarioModel> usuarioOp = Optional.ofNullable(usuarioService.findUsuarioById(newReclamo.getUsuario().getIdUsuario()));
        if(usuarioOp.isEmpty()){
            log.error("El usuario de ID: " + newReclamo.getUsuario().getIdUsuario() + " no se encuentra en la base de datos.");
            throw new Exception("El usuario no se encuentra en la base de datos.");
        }

        UsuarioModel usuarioDb = usuarioOp.get();

        Optional<EdificioModel> edificioOp = Optional.ofNullable(edificioService.findEdificioById((newReclamo.getEdificio().getIdEdificio())));

        if(edificioOp.isEmpty()){
            log.error("El edificio no se encuentra en la base de datos.");
            throw new Exception("El edificio no se encuentra en la base de datos.");
        }

        if(newReclamo.getUnidad()!=null) {
            Optional<UnidadModel> unidadOp = Optional.ofNullable(unidadService.findUnidadById(newReclamo.getUnidad().getIdUnidad()));

            if (unidadOp.isEmpty()) {
                log.error("La unidad no se encuentra en la base de datos.");
                throw new Exception("La unidad no se encuentra en la base de datos.");
            }
        }

        if (newReclamo.getEspacioComun()!=null) {
            Optional<EspacioComunModel> espacioComunOp = Optional.ofNullable(espacioComunService.findEspacioComunById(newReclamo.getEspacioComun().getIdEspacioComun()));

            if (espacioComunOp.isEmpty()) {
                log.error("El espacio común no se encuentra en la base de datos.");
                throw new Exception("El espacio común no se encuentra en la base de datos.");
            }
        }

        if(newReclamo.getUnidad() != null && newReclamo.getEspacioComun() != null){
            throw new Exception("El reclamo no cuenta con un espacio válido.");
        }

        if(newReclamo.getUnidad() == null && newReclamo.getEspacioComun() == null){
            throw new Exception("El reclamo debe tener un solo espacio.");
        }

        if(usuarioDb.getTipoUsuario().equals(TipoUsuario.DUENIO) && newReclamo.getUnidad().getInquilino() != null){

        }

        return this.reclamoRepository.save(newReclamo);
    }

    public ReclamoModel updateReclamo(ReclamoModel reclamo, Long id) throws Exception {
        log.info("Id ingresado: " + id);
        if (id <= 0){
            log.info("El id ingresado no es valido.");
            throw new Exception("El id ingresado no es valido");
        }

        Optional<ReclamoModel> reclamoOp = this.reclamoRepository.findById(reclamo.getIdReclamo());

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
        Optional<ReclamoModel> reclamoOp = this.reclamoRepository.findById(id);
        if (reclamoOp.isEmpty()) {
            throw new Exception("El reclamo de id: " + id + " no se encuentra en la BD");
        }
        return reclamoOp.get();
    }

    public List<ReclamoModel> findAllReclamos() {
        return this.reclamoRepository.findAll();
    }
}
