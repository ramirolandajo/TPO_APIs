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
    private Long idEspacioComun;
    private int piso;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "Edificio_Id")
    private EdificioModel edificio;
}
