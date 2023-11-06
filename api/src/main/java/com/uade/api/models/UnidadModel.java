package com.uade.api.models;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unidades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUnidad;
    private int piso;
    private int numero;
    @ManyToOne
    private UsuarioModel duenio;
    @ManyToOne
    private UsuarioModel inquilino;
    @ManyToOne
    @JoinColumn(name = "idEdificio")
    @JsonIdentityReference(alwaysAsId = true)
    private EdificioModel edificio;

    public UnidadModel(int piso, int numero, UsuarioModel duenio, UsuarioModel inquilino, EdificioModel edificio) {
        this.piso = piso;
        this.numero = numero;
        this.duenio = duenio;
        this.inquilino = inquilino;
        this.edificio = edificio;
    }

    public UnidadModel(int piso, int numero, UsuarioModel duenio, EdificioModel edificio) {
        this.piso = piso;
        this.numero = numero;
        this.duenio = duenio;
        this.edificio = edificio;
    }
}
