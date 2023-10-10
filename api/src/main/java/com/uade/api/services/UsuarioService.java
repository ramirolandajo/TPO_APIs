package com.uade.api.services;

import com.uade.api.models.UsuarioModel;
import com.uade.api.repositories.IUsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class UsuarioService {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    public UsuarioModel createUsuario(UsuarioModel newUsuario) throws Exception {
        Optional<UsuarioModel> usuarioOp = this.usuarioRepository.findUserByCuil(newUsuario.getCuil());
        if (usuarioOp.isPresent()) {
            log.error("El usuario que esta intentando crear ya se encuentra en la base de datos");
            throw new Exception("El usuario que esta intentando crear ya se encuentra en la base de datos");
        }
        return this.usuarioRepository.save(newUsuario);
    }

    public UsuarioModel updateUsuario(UsuarioModel usuario, Long id) throws Exception {
        log.info("Id ingresado: " + id);
        if (id <= 0){
            log.error("El id ingresado no es valido.");
            throw new Exception("El id ingresado no es valido");
        }

        Optional<UsuarioModel> usuarioOp = this.usuarioRepository.findById(usuario.getIdUsuario());

        if (usuarioOp.isEmpty()) {
            log.error("El usuario que intenta actualizar no se encuentra en la base de datos");
            throw new Exception("El usuario que intenta actualizar no se encuentra en la base de datos");
        }

        UsuarioModel usuarioDb = usuarioOp.get();
        usuarioDb.setUsuario(usuario.getUsuario());
        usuarioDb.setTipoUsuario(usuario.getTipoUsuario());
        usuarioDb.setCuil(usuario.getCuil());
        usuarioDb.setPassword(usuario.getPassword());
        usuarioDb.setNombreCompleto(usuario.getNombreCompleto());

        log.info("El usuario actualizado: " + usuarioDb);
        return this.usuarioRepository.save(usuarioDb);
    }

    public UsuarioModel findUsuario(String usuario, String password){
        Optional<UsuarioModel> userOp = usuarioRepository.findUserByUsuario(usuario);

        if(userOp.isPresent() && checkPassword(password, userOp.get().getPassword())) {
            return userOp.get();
        } else {
            return null;
        }
    }

    private boolean checkPassword(String password, String passwordDB) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //String hashedPassword = passwordEncoder.encode(password);
        System.out.println("Password: " + password);
        //System.out.println("hashedPassword: " + hashedPassword);
        System.out.println("passwordDB: " + passwordDB);
        boolean passwordMatches = passwordEncoder.matches(password, passwordDB);

        return passwordMatches;
    }
    public UsuarioModel findUsuarioById(Long id) throws Exception {
        log.info("Id ingresado: " + id);
        if (id <= 0){
            log.error("El id ingresado no es valido.");
            throw new Exception("El id ingresado no es valido");
        }
        Optional<UsuarioModel> usuarioOp = this.usuarioRepository.findById(id);
        if (usuarioOp.isEmpty()) {
            log.error("El usuario de id: " + id + " no se encuentra en la BD");
            throw new Exception("El usuario de id: " + id + " no se encuentra en la BD");
        }
        return usuarioOp.get();
    }

    public List<UsuarioModel> findAllUsuarios() {
        return this.usuarioRepository.findAll();
    }
}
