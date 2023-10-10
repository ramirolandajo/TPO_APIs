package com.uade.api.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UsuarioModelDTO {
    private String usuario;
    private String password;
}
