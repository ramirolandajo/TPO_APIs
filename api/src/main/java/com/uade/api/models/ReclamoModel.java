package com.uade.api.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reclamos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReclamoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReclamo;
    private String estado;
    private String descripcion;
    @OneToOne
    private UsuarioModel usuario;
    @OneToOne
    private EdificioModel edificio;
    @OneToOne
    private UnidadModel unidad;
    @OneToOne
    private EspacioComunModel espacioComun;
}
