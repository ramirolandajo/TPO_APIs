package com.uade.api.services;

import com.uade.api.dao.IUnidadModelDAO;
import com.uade.api.models.UnidadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadService implements IUnidadService{
    @Autowired
    private IUnidadModelDAO unidadDAO;
    @Override
    public List<UnidadModel> findAll() {
        List<UnidadModel> unidades = unidadDAO.findAll();
        return unidades;
    }

    @Override
    public UnidadModel findById(int id) {
        UnidadModel unidad = unidadDAO.findById(id);
        return unidad;
    }

    @Override
    public void save(UnidadModel unidad) {
        unidadDAO.save(unidad);
    }

    @Override
    public void update(int idUnidad, UnidadModel unidad) {
        UnidadModel unidadExistente = unidadDAO.findById(idUnidad);

        if(unidadExistente != null){
            unidadExistente.setNumero(unidad.getNumero());
            unidadExistente.setPiso(unidad.getPiso());
            unidadExistente.setDuenio(unidad.getDuenio());
            unidadExistente.setInquilino(unidad.getInquilino());
            unidadExistente.setEdificio(unidad.getEdificio());
            //No existe Getter ni Setter del atributo "habitada".

            unidadDAO.save(unidadExistente);
        }
    }

    @Override
    public void deleteById(int id) {
        unidadDAO.deleteById(id);
    }
}
