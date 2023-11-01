package com.uade.api.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "edificios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idEdificio")
public class EdificioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEdificio;
    private String direccion;
    @OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
    private List<UnidadModel> unidades;
    @OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
    private List<EspacioComunModel> espaciosComunes;

}
