package com.uade.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspacioComun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEspacioComun;
    private int piso;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "Edificio_Id")
    private EdificioModel edificio;

    public EspacioComun(int piso, String descripcion, EdificioModel edificio) {
        this.piso = piso;
        this.descripcion = descripcion;
        this.edificio = edificio;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EdificioModel getEdificio() {
        return edificio;
    }

    public void setEdificio(EdificioModel edificio) {
        this.edificio = edificio;
    }
}
