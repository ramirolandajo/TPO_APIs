package com.uade.api.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EspacioComunDTO {
    private int piso;
    private String descripcion;
    private Long idEdificio;

}
