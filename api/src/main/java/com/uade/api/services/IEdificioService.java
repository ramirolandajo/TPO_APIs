package com.uade.api.services;

import com.uade.api.models.Entity.EdificioModel;

import java.util.List;

public interface IEdificioService {
    public List<EdificioModel> findAll();
    public EdificioModel findById(int id);
    public void save(EdificioModel edificio);
    public void update(int idEdificio, EdificioModel edificio);
    public void deleteById(int id);
}
