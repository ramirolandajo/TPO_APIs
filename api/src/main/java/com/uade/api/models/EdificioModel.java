package com.uade.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "edificios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EdificioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEdificio;
    private String direccion;
    @OneToMany
    private List<UnidadModel> unidades;
    @OneToMany
    private List<EspacioComun> espaciosComunes;
    public boolean soyEseEdificio(int id){
        return false;
    }
}
