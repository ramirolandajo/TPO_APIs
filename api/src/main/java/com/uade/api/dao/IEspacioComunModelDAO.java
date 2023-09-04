package com.uade.api.dao;

import com.uade.api.models.EspacioComunModel;

import java.util.List;

public interface IEspacioComunModelDAO {
    public List<EspacioComunModel> findAll();
    public EspacioComunModel findById(int id);
    public void save(EspacioComunModel espacioComun);
    public void deleteById(int id);
}
