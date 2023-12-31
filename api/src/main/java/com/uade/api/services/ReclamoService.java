package com.uade.api.services;

import com.uade.api.models.*;
import com.uade.api.repositories.IReclamoRepository;
import com.uade.api.repositories.IUsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
            // verificamos que el usuario exista en la BD
            log.error("El usuario de ID: " + newReclamo.getUsuario().getIdUsuario() + " no se encuentra en la base de datos.");
            throw new Exception("El usuario no se encuentra en la base de datos.");
        }

        UsuarioModel usuarioDb = usuarioOp.get();
        Optional<EdificioModel> edificioOp = Optional.ofNullable(edificioService.findEdificioById((newReclamo.getEdificio().getIdEdificio())));

        if(edificioOp.isEmpty()){
            // verificamos que el edificio exista en la BD
            log.error("El edificio con ID: " + newReclamo.getEdificio().getIdEdificio() + " no se encuentra en la base de datos.");
            throw new Exception("El edificio con ID: " + newReclamo.getEdificio().getIdEdificio() + " no se encuentra en la base de datos.");
        }

        // verificamos que el reclamo sea sobre una unidad o sobre un espacio comun
        if (newReclamo.getUnidad() == null && newReclamo.getEspacioComun() == null) {
            log.error("El reclamo debe tener un espacio valido (unidad o espacio comun)");
            throw new Exception("El reclamo debe tener un espacio valido (unidad o espacio comun)");
        }
        // verificamos que el reclamo no sea sobre una unidad Y un espacio comun
        if(newReclamo.getUnidad() != null && newReclamo.getEspacioComun() != null){
            log.error("El reclamo no cuenta con un espacio válido.");
            throw new Exception("El reclamo no cuenta con un espacio válido.");
        }

        // verificamos que la tenga unidad y este en la BD
        if(newReclamo.getUnidad()!=null) {
            Optional<UnidadModel> unidadOp = Optional.ofNullable(unidadService.findUnidadById(newReclamo.getUnidad().getIdUnidad()));
            if (unidadOp.isEmpty()) {
                log.error("La unidad de ID: " + newReclamo.getUnidad().getIdUnidad() + " no se encuentra en la base de datos.");
                throw new Exception("La unidad de ID: " + newReclamo.getUnidad().getIdUnidad() + " no se encuentra en la base de datos.");
            }

            if (newReclamo.getUnidad().getInquilino() == null) {
                if (!usuarioDb.getTipoUsuario().equals("DUENIO")){
                    log.error("La unidad no posee inquilino. El usuario que crea el reclamo debe ser de tipo DUENIO");
                    throw new Exception("La unidad no posee inquilino. El usuario que crea el reclamo debe ser de tipo DUENIO");
                }
                // verificamos que si el usuario es de tipo DUENIO que sea el duenio de la unidad
                else {
                    if (!usuarioDb.equals(newReclamo.getUnidad().getDuenio())){
                        log.error("Un usuario duenio no puede crear un reclamo sobre una unidad que no es su propiedad");
                        throw new Exception("Un usuario duenio no puede crear un reclamo sobre una unidad que no es su propiedad");
                    }
                }
            }
            else {
                if (!usuarioDb.getTipoUsuario().equals("INQUILINO")) {
                    log.error("La unidad posee un inquilino. Solo el usuario de tipo inquilino puede generar el reclamo");
                    throw new Exception("La unidad posee un inquilino. Solo el usuario de tipo inquilino puede generar el reclamo");
                }
                // verificamos si el usuario es INQUILINO que sea el inquilino de la unidad
                else {
                    if (!usuarioDb.equals(newReclamo.getUnidad().getInquilino())) {
                        log.error("El inquilino no pertenece a la unidad del reclamo por lo que no puede realizar el reclamo");
                        throw new Exception("El inquilino no pertenece a la unidad del reclamo por lo que no puede realizar el reclamo");
                    }
                }
            }
        }

        //verificamos que si tiene espacio comun este en la BD
        if (newReclamo.getEspacioComun()!=null) {
            Optional<EspacioComunModel> espacioComunOp = Optional.ofNullable(espacioComunService.findEspacioComunById(newReclamo.getEspacioComun().getIdEspacioComun()));
            if (espacioComunOp.isEmpty()) {
                log.error("El espacio común no se encuentra en la base de datos.");
                throw new Exception("El espacio común no se encuentra en la base de datos.");
            }
        }

        return this.reclamoRepository.save(newReclamo);
    }

    public ReclamoModel updateEstadoReclamo(String estadoReclamo, Long id) throws Exception {
        log.info("Id ingresado: " + id);
        if (id <= 0){
            log.info("El id ingresado no es valido.");
            throw new Exception("El id ingresado no es valido");
        }

        Optional<ReclamoModel> reclamoOp = this.reclamoRepository.findById(id);

        if (reclamoOp.isEmpty()) {
            log.info("El reclamo que intenta actualizar no se encuentra en la base de datos");
            throw new Exception("El reclamo que intenta actualizar no se encuentra en la base de datos");
        }

        ReclamoModel reclamoDb = reclamoOp.get();
        reclamoDb.setEstado(estadoReclamo);

        log.info("El reclamo actualizado: " + reclamoDb);
        return this.reclamoRepository.save(reclamoDb);
    }

    public String deleteReclamo(Long id) throws Exception{
        Optional<ReclamoModel> reclamoOp = this.reclamoRepository.findById(id);
        if(reclamoOp.isEmpty()){
            log.error("El reclamo que intenta borrar no se encuentra en la base de datos.");
            throw new Exception("El reclamo que intenta borrar no se encuentra en la base de datos.");
        }

        ReclamoModel reclamoDb = reclamoOp.get();

        this.reclamoRepository.delete(reclamoDb);
        return "Reclamo eliminado con éxito!";
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

    public List<ReclamoModel> findAllReclamosFromUser(Long id) {
        List<ReclamoModel> allReclamos = this.reclamoRepository.findAll();
        List<ReclamoModel> reclamosFromUser = new ArrayList<>();
        for (ReclamoModel reclamo : allReclamos) {
            if (Objects.equals(reclamo.getUsuario().getIdUsuario(), id)) {
                reclamosFromUser.add(reclamo);
            }
        }
        return reclamosFromUser;
    }
}
