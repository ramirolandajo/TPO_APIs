package com.uade.api.dao;

import com.uade.api.models.ReclamoModel;

import java.util.List;

public interface IReclamoModelDAO {
    public List<ReclamoModel> findAll();
    public ReclamoModel findById(int id);
    public void save(ReclamoModel reclamo);
    public void deleteById(int id);
}
