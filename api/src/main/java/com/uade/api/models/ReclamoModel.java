package com.uade.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "reclamos")
@Data
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

    public ReclamoModel(String estado, String descripcion, UsuarioModel usuario, EdificioModel edificio, UnidadModel unidad) {
        this.estado = estado;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.edificio = edificio;
        this.unidad = unidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public EdificioModel getEdificio() {
        return edificio;
    }

    public void setEdificio(EdificioModel edificio) {
        this.edificio = edificio;
    }

    public UnidadModel getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadModel unidad) {
        this.unidad = unidad;
    }
}
