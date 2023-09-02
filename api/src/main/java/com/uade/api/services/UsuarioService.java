package com.uade.api.services;

import com.uade.api.models.DAO.IUsuarioModelDAO;
import com.uade.api.models.Entity.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService{
    @Autowired
    private IUsuarioModelDAO usuarioDAO;
    @Override
    public List<UsuarioModel> findAll() {
        List<UsuarioModel> usuarios = usuarioDAO.findAll();
        return usuarios;
    }

    @Override
    public UsuarioModel findById(int id) {
        UsuarioModel usuario = usuarioDAO.findById(id);
        return usuario;
    }

    @Override
    public void save(UsuarioModel usuario) {
        usuarioDAO.save(usuario);
    }

    @Override
    public void update(int idUsuario, UsuarioModel usuario) {
        UsuarioModel usuarioExistente = usuarioDAO.findById(idUsuario);

        if(usuarioExistente != null){
            usuarioExistente.setUsuario(usuario.getUsuario());
            usuarioExistente.setPassword(usuario.getPassword());
            usuarioExistente.setCuil(usuario.getCuil());
            usuarioExistente.setNombreCompleto(usuario.getNombreCompleto());
            usuarioExistente.setTipoUsuario(usuario.getTipoUsuario());

            usuarioDAO.save(usuarioExistente);
        }
    }

    @Override
    public void deleteById(int id) {
        usuarioDAO.deleteById(id);
    }
}
