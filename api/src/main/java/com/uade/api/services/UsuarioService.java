package com.uade.api.services;

import com.uade.api.models.UsuarioModel;
import com.uade.api.repositories.IUsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class UsuarioService {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    public UsuarioModel createUsuario(UsuarioModel newUsuario) throws Exception {
        Optional<UsuarioModel> usuarioOp = usuarioRepository.findUserByCuil(newUsuario.getCuil());
        if (usuarioOp.isPresent()) {
            throw new Exception("El usuario que esta intentando crear ya se encuentra en la base de datos");
        }
        return this.usuarioRepository.save(newUsuario);
    }

    public UsuarioModel updateUsuario(UsuarioModel usuario, Long id) throws Exception {
        log.info("Id ingresado: " + id);
        if (id <= 0){
            log.info("El id ingresado no es valido.");
            throw new Exception("El id ingresado no es valido");
        }

        Optional<UsuarioModel> usuarioOp = usuarioRepository.findById(usuario.getId_usuario());

        if (usuarioOp.isEmpty()) {
            log.info("El usuario que intenta actualizar no se encuentra en la base de datos");
            throw new Exception("El usuario que intenta actualizar no se encuentra en la base de datos");
        }

        UsuarioModel usuarioDb = usuarioOp.get();
        usuarioDb.setUsuario(usuario.getUsuario());
        usuarioDb.setTipo_usuario(usuario.getTipo_usuario());
        usuarioDb.setCuil(usuario.getCuil());
        usuarioDb.setPassword(usuario.getPassword());
        usuarioDb.setNombre_completo(usuario.getNombre_completo());

        log.info("El usuario actualizado: " + usuarioDb);
        return this.usuarioRepository.save(usuarioDb);
    }

    public UsuarioModel findUsuarioById(Long id) throws Exception {
        log.info("Id ingresado: " + id);
        if (id <= 0){
            log.info("El id ingresado no es valido.");
            throw new Exception("El id ingresado no es valido");
        }
        Optional<UsuarioModel> usuarioOp = usuarioRepository.findById(id);
        if (usuarioOp.isEmpty()) {
            throw new Exception("El usuario de id: " + id + " no se encuentra en la BD");
        }
        return usuarioOp.get();
    }

    public List<UsuarioModel> findAllUsuarios() {
        return this.usuarioRepository.findAll();
    }
}
