package com.uade.api.services;

import com.uade.api.dao.IReclamoModelDAO;
import com.uade.api.models.ReclamoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReclamoService implements IReclamoService{

    @Autowired
    private IReclamoModelDAO reclamoDAO;
    @Override
    public List<ReclamoModel> findAll() {
        List<ReclamoModel> reclamos = reclamoDAO.findAll();
        return reclamos;
    }

    @Override
    public ReclamoModel findById(int id) {
        ReclamoModel reclamo = reclamoDAO.findById(id);
        return reclamo;
    }

    @Override
    public void save(ReclamoModel reclamo) {
        reclamoDAO.save(reclamo);
    }

    @Override
    public void update(int idReclamo, ReclamoModel reclamo) {
        ReclamoModel reclamoExistente = reclamoDAO.findById(idReclamo);

        if(reclamoExistente != null){
            reclamoExistente.setEstado(reclamo.getEstado());
            reclamoExistente.setDescripcion(reclamo.getDescripcion());
            reclamoExistente.setEdificio(reclamo.getEdificio());
            reclamoExistente.setUnidad(reclamo.getUnidad());
            reclamoExistente.setUsuario(reclamo.getUsuario());
            reclamoDAO.save(reclamoExistente);
        }
    }

    @Override
    public void deleteById(int id) {
        reclamoDAO.deleteById(id);
    }
}
