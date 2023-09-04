package com.uade.api.dao;

import com.uade.api.models.EspacioComunModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EspacioComunModelImpl implements IEspacioComunModelDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<EspacioComunModel> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<EspacioComunModel> getQuery = currentSession.createQuery("from espacios_comunes", EspacioComunModel.class);
        List<EspacioComunModel> espaciosComunes = getQuery.getResultList();

        return espaciosComunes;
    }

    @Override
    @Transactional(readOnly = true)
    public EspacioComunModel findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        EspacioComunModel espacioComun = currentSession.get(EspacioComunModel.class, id);

        return espacioComun;
    }

    @Override
    @Transactional
    public void save(EspacioComunModel espacioComun) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.persist(espacioComun);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from espacios_comunes where id=:idEspacioComun");
        theQuery.setParameter("idEspacioComun", id);
        theQuery.executeUpdate();
    }
}
