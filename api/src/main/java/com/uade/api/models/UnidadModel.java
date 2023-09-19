package com.uade.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "unidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_unidad;
    private int piso;
    private int numero;
    private boolean habitada;
    @OneToOne
    private UsuarioModel duenio;
    @OneToOne
    private UsuarioModel inquilino;
    @ManyToOne
    @JoinColumn(name = "id_edificio")
    private EdificioModel edificio;
}
