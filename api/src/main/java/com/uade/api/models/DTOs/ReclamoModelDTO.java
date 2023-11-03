package com.uade.api.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReclamoModelDTO {
    private String estado;
    private String descripcion;
    private Long idUsuario;
    private Long idEdificio;
    private Long idUnidad;
    private Long idEspacioComun;

}
