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
    private Long idUnidad;
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

    public UnidadModel(int piso, int numero, boolean habitada, UsuarioModel duenio, UsuarioModel inquilino, EdificioModel edificio) {
        this.piso = piso;
        this.numero = numero;
        this.habitada = habitada;
        this.duenio = duenio;
        this.inquilino = inquilino;
        this.edificio = edificio;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isHabitada() {
        return habitada;
    }

    public void setHabitada(boolean habitada) {
        this.habitada = habitada;
    }

    public UsuarioModel getDuenio() {
        return duenio;
    }

    public void setDuenio(UsuarioModel duenio) {
        this.duenio = duenio;
    }

    public UsuarioModel getInquilino() {
        return inquilino;
    }

    public void setInquilino(UsuarioModel inquilino) {
        this.inquilino = inquilino;
    }

    public EdificioModel getEdificio() {
        return edificio;
    }

    public void setEdificio(EdificioModel edificio) {
        this.edificio = edificio;
    }
}
