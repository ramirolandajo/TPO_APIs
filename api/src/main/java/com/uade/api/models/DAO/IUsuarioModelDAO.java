package com.uade.api.models.DAO;

import com.uade.api.models.Entity.UsuarioModel;

import java.util.List;

public interface IUsuarioModelDAO {
    public List<UsuarioModel> findAll();
    public UsuarioModel findById(int id);
    public void save(UsuarioModel usuario);
    public void deleteById(int id);
}
