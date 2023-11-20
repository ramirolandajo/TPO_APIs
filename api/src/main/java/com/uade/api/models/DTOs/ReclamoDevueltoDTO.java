package com.uade.api.models.DTOs;

import com.uade.api.models.ImagenModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReclamoDevueltoDTO {
    private Long idReclamo;
    private String estado;
    private String descripcion;
    private String edificio;
    private String lugarReclamo;
    private ImagenModel imagen;
}