package com.uade.api.dao;

import com.uade.api.models.UnidadModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UnidadModelImpl implements IUnidadModelDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<UnidadModel> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<UnidadModel> getQuery = currentSession.createQuery("from unidades", UnidadModel.class);
        List<UnidadModel> unidades = getQuery.getResultList();

        return unidades;
    }

    @Override
    @Transactional(readOnly = true)
    public UnidadModel findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        UnidadModel unidad = currentSession.get(UnidadModel.class, id);

        return unidad;
    }

    @Override
    @Transactional
    public void save(UnidadModel unidad) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.persist(unidad);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from unidades where id=:idUnidad");
        theQuery.setParameter("idUnidad", id);
        theQuery.executeUpdate();
    }
}
