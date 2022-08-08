package com.cursojava.curso.dao;

import ch.qos.logback.classic.joran.action.EvaluatorAction;
import com.cursojava.curso.Models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImpl implements UsuarioDao{


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        return  entityManager.createQuery(query).getResultList();

    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    public Usuario obtenerUsuarioCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email";
       List<Usuario> lista = entityManager.createQuery(query).setParameter("email", usuario.getEmail()).getResultList();
       if(lista.isEmpty()){

           return null;

       }
       String passwordHasheado = lista.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(passwordHasheado,usuario.getPassword())){
            return lista.get(0);
        }
        return null;
    }

    @Override
    public void editar(Usuario usuario) {
            entityManager.merge(usuario);

    }

}
