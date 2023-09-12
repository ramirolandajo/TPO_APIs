package com.uade.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Table(name = "unidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUnidad;
    private int piso;
    private int numero;
    private boolean habitada;
    @OneToOne
    private UsuarioModel duenio;
    @OneToOne
    private UsuarioModel inquilino;
    @ManyToOne
    @JoinColumn(name = "Edificio_Id")
    private EdificioModel edificio;
}
