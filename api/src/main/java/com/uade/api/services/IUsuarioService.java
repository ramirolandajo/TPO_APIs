package com.uade.api.services;

import com.uade.api.models.Entity.UsuarioModel;

import java.util.List;

public interface IUsuarioService {
    public List<UsuarioModel> findAll();
    public UsuarioModel findById(int id);
    public void save(UsuarioModel usuario);
    public void update(int idUsuario, UsuarioModel usuario);
    public void deleteById(int id);
}
