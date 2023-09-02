package com.uade.api.models.DAO;

import com.uade.api.models.Entity.UnidadModel;

import java.util.List;

public interface IUnidadModelDAO {
    public List<UnidadModel> findAll();
    public UnidadModel findById(int id);
    public void save(UnidadModel unidad);
    public void deleteById(int id);
}
