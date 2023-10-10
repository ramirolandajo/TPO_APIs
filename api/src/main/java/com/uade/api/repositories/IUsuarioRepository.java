package com.uade.api.repositories;

import com.uade.api.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    Optional<UsuarioModel> findUserByCuil(String cuil);
    UsuarioModel findUsuario(String usuario, String password);
}
