package com.uade.api.models.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EdificioDevueltoDTO {
    private Long idEdificio;
    private String direccion;
    private int unidades;
    private int espaciosComunes;
}
