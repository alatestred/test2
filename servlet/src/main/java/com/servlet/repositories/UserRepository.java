package com.servlet.repositories;

import com.servlet.domain.ManagerFactory;
import com.servlet.domain.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author Daulet Zholtayev
 * @since 18.11.2020 - 20:04
 */
@Stateless
@LocalBean
public class UserRepository {

    public User findByLogin(String login) {
        //JPQL
        TypedQuery<User> query = ManagerFactory.get()
                .createNamedQuery("findByLogin", User.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }

//    public void create(User user) {
//        // persist - insert
//        em.persist(user);
//    }
//
//    public void update(User user) {
//        // merge - update  or insert if doesn't exists
//        em.merge(user);
//    }


}
