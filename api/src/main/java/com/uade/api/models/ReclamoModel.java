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
    @ManyToOne
    private UsuarioModel usuario;
    @ManyToOne
    private EdificioModel edificio;
    @ManyToOne
    private UnidadModel unidad;
    @ManyToOne
    private EspacioComunModel espacioComun;
    @OneToOne
    private ImagenModel imagen;

    public ReclamoModel(String estado, String descripcion, UsuarioModel usuario, EdificioModel edificio, UnidadModel unidad, EspacioComunModel espacioComun, ImagenModel imagen) {
        this.estado = estado;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.edificio = edificio;
        this.unidad = unidad;
        this.espacioComun = espacioComun;
        this.imagen = imagen;
    }
}
