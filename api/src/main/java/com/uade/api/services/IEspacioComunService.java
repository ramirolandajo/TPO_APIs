package com.uade.api.services;

import com.uade.api.models.EspacioComunModel;

import java.util.List;

public interface IEspacioComunService {
    public List<EspacioComunModel> findAll();
    public EspacioComunModel findById(int id);
    public void save(EspacioComunModel espacioComun);
    public void update(int idEspacioComun, EspacioComunModel espacioComun);
    public void deleteById(int id);
}
