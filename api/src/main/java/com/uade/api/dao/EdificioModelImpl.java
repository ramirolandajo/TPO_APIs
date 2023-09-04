package com.uade.api.dao;

import com.uade.api.models.EdificioModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EdificioModelImpl implements IEdificioModelDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<EdificioModel> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<EdificioModel> getQuery = currentSession.createQuery("from edificios", EdificioModel.class);
        List<EdificioModel> edificios = getQuery.getResultList();

        return edificios;
    }

    @Override
    @Transactional(readOnly = true)
    public EdificioModel findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        EdificioModel edificio = currentSession.get(EdificioModel.class, id);

        return edificio;
    }

    @Override
    @Transactional
    public void save(EdificioModel edificio) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.persist(edificio);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from edificios where id=:idEdificio");
        theQuery.setParameter("idEdificio", id);
        theQuery.executeUpdate();
    }
}
