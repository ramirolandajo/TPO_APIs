package com.uade.api.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EspacioComunDevueltoDTO {
    private Long idEspacioComun;
    private Long idEdificio;
    private int piso;
    private String descripcion;
}
