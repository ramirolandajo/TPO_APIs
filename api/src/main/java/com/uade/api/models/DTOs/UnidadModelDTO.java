package com.uade.api.models.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadModelDTO {
    private int piso;
    private int numero;
    private Long idDuenio; // id del duenio
    private Long idInquilino;  // id del inquilino
    private Long idEdificio;   // id del edificio

}
