package com.uade.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Table(name = "espaciosComunes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspacioComunModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEspacioComun;
    private int piso;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "Edificio_Id")
    private EdificioModel edificio;
}
