package com.uade.api.dao;

import com.uade.api.models.EdificioModel;
import java.util.List;

public interface IEdificioModelDAO {
    public List<EdificioModel> findAll();
    public EdificioModel findById(int id);
    public void save(EdificioModel edificio);
    public void deleteById(int id);
}
