package com.uade.api.models.DAO;

import com.uade.api.models.Entity.EspacioComunModel;

import java.util.List;

public interface IEspacioComunModelDAO {
    public List<EspacioComunModel> findAll();
    public EspacioComunModel findById(int id);
    public void save(EspacioComunModel espacioComun);
    public void deleteById(int id);
}
