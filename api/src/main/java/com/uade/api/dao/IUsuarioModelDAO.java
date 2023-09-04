package com.uade.api.dao;

import com.uade.api.models.UsuarioModel;

import java.util.List;

public interface IUsuarioModelDAO {
    public List<UsuarioModel> findAll();
    public UsuarioModel findById(int id);
    public void save(UsuarioModel usuario);
    public void deleteById(int id);
}
