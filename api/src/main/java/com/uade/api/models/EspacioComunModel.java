package com.uade.api.models;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "espacios_comunes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EspacioComunModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEspacioComun;
    private int piso;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "idEdificio")
    private EdificioModel edificio;

    public EspacioComunModel(int piso, String descripcion, EdificioModel edificio) {
        this.piso = piso;
        this.descripcion = descripcion;
        this.edificio = edificio;
    }
}
