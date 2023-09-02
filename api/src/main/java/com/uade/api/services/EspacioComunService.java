package com.uade.api.services;

import com.uade.api.models.DAO.IEspacioComunModelDAO;
import com.uade.api.models.Entity.EspacioComunModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspacioComunService implements IEspacioComunService{

    @Autowired
    private IEspacioComunModelDAO espacioComunDAO;

    @Override
    public List<EspacioComunModel> findAll() {
        List<EspacioComunModel> espaciosComunes = espacioComunDAO.findAll();
        return espaciosComunes;
    }

    @Override
    public EspacioComunModel findById(int id) {
        EspacioComunModel espacioComun = espacioComunDAO.findById(id);
        return espacioComun;
    }

    @Override
    public void save(EspacioComunModel espacioComun) {
        espacioComunDAO.save(espacioComun);
    }

    @Override
    public void update(int idEspacioComun, EspacioComunModel espacioComun) {
        EspacioComunModel espacioComunExistente = espacioComunDAO.findById(idEspacioComun);

        if(espacioComunExistente != null){
            espacioComunExistente.setPiso(espacioComunExistente.getPiso());
            espacioComunExistente.setDescripcion(espacioComun.getDescripcion());
            espacioComunExistente.setEdificio(espacioComun.getEdificio());
            espacioComunDAO.save(espacioComunExistente);
        }

    }

    @Override
    public void deleteById(int id) {
        espacioComunDAO.deleteById(id);
    }
}
