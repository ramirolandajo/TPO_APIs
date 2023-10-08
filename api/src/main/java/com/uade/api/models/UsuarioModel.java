package com.uade.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String usuario;
    private String password;
    private String cuil;
    private String nombreCompleto;
    private TipoUsuario tipoUsuario;

}
