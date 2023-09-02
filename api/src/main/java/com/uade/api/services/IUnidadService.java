package com.uade.api.services;

import com.uade.api.models.Entity.UnidadModel;

import java.util.List;

public interface IUnidadService {
    public List<UnidadModel> findAll();
    public UnidadModel findById(int id);
    public void save(UnidadModel unidad);
    public void update(int idUnidad, UnidadModel unidad);
    public void deleteById(int id);
}
