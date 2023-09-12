package com.uade.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Table(name = "reclamos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReclamoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReclamo;
    private String estado;
    private String descripcion;
    @OneToOne
    private UsuarioModel usuario;
    @OneToOne
    private EdificioModel edificio;
    @OneToOne
    private UnidadModel unidad;
}