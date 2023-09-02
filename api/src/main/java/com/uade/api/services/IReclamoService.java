package com.uade.api.services;

import com.uade.api.models.Entity.ReclamoModel;

import java.util.List;

public interface IReclamoService {
    public List<ReclamoModel> findAll();
    public ReclamoModel findById(int id);
    public void save(ReclamoModel reclamo);
    public void update(int idReclamo, ReclamoModel reclamo);
    public void deleteById(int id);
}
