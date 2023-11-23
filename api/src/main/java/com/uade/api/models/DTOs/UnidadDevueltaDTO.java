package com.uade.api.models.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadDevueltaDTO {
    private Long idUnidad;
    private int piso;
    private int numero;
    private Long idDuenio; // id del duenio
    private Long idInquilino;  // id del inquilino
    private Long idEdificio;   // id del edificio

    public UnidadDevueltaDTO(Long idUnidad, int piso, int numero, Long idDuenio, Long idEdificio) {
        this.idUnidad = idUnidad;
        this.piso = piso;
        this.numero = numero;
        this.idDuenio = idDuenio;
        this.idEdificio = idEdificio;
    }
}