package com.uade.api.dao;

import com.uade.api.models.UnidadModel;

import java.util.List;

public interface IUnidadModelDAO {
    public List<UnidadModel> findAll();
    public UnidadModel findById(int id);
    public void save(UnidadModel unidad);
    public void deleteById(int id);
}
