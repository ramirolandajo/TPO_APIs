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
    @OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
    private List<UnidadModel> unidades;
    @OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
    private List<EspacioComun> espaciosComunes;

    public EdificioModel(String direccion, List<UnidadModel> unidades, List<EspacioComun> espaciosComunes) {
        this.direccion = direccion;
        this.unidades = unidades;
        this.espaciosComunes = espaciosComunes;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<UnidadModel> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<UnidadModel> unidades) {
        this.unidades = unidades;
    }

    public List<EspacioComun> getEspaciosComunes() {
        return espaciosComunes;
    }

    public void setEspaciosComunes(List<EspacioComun> espaciosComunes) {
        this.espaciosComunes = espaciosComunes;
    }

    public boolean soyEseEdificio(int id){
        return this.idEdificio==id;
    }


}
