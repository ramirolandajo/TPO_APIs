package com.uade.api.models.DAO;

import com.uade.api.models.Entity.ReclamoModel;

import java.util.List;

public interface IReclamoModelDAO {
    public List<ReclamoModel> findAll();
    public ReclamoModel findById(int id);
    public void save(ReclamoModel reclamo);
    public void deleteById(int id);
}
