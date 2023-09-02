package com.uade.api.models.DAO;

import com.uade.api.models.Entity.EdificioModel;
import java.util.List;

public interface IEdificioModelDAO {
    public List<EdificioModel> findAll();
    public EdificioModel findById(int id);
    public void save(EdificioModel edificio);
    public void deleteById(int id);
}
