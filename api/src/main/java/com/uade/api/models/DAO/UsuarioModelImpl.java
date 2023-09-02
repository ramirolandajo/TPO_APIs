package com.uade.api.models.DAO;

import com.uade.api.models.Entity.UsuarioModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UsuarioModelImpl implements IUsuarioModelDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioModel> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<UsuarioModel> getQuery = currentSession.createQuery("from usuarios", UsuarioModel.class);
        List<UsuarioModel> usuarios = getQuery.getResultList();

        return usuarios;
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioModel findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        UsuarioModel usuario = currentSession.get(UsuarioModel.class, id);

        return usuario;
    }

    @Override
    @Transactional
    public void save(UsuarioModel usuario) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.persist(usuario);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from usuarios where id=:idUsuario");
        theQuery.setParameter("idUsuario", id);
        theQuery.executeUpdate();
    }
}
