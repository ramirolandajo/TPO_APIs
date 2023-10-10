package com.uade.api.repositories;

import com.uade.api.models.UsuarioModel;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public abstract class UsuarioRepositoryImpl implements IUsuarioRepository{
    @Autowired
    private EntityManager entityManager;
    @Override
    public UsuarioModel findUsuario(String usuario, String password) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<UsuarioModel> theQuery = currentSession.createQuery("FROM Usuario WHERE usuario=:usuario", UsuarioModel.class);
        theQuery.setParameter("usuario", usuario);

        UsuarioModel user = theQuery.uniqueResult();

        if(user != null && checkPassword(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    private boolean checkPassword(String password, String passwordDB) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //String hashedPassword = passwordEncoder.encode(password);
        System.out.println("Password: " + password);
        //System.out.println("hashedPassword: " + hashedPassword);
        System.out.println("passwordDB: " + passwordDB);
        boolean isPasswordMatch = passwordEncoder.matches(password, passwordDB);

        return isPasswordMatch;
    }
}
