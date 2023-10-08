package com.uade.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "espacios_comunes")
@Data
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
}
