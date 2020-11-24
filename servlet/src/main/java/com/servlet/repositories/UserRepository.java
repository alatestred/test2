package com.servlet.repositories;

import com.servlet.domain.ManagerFactory;
import com.servlet.domain.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

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


    public void createUser(String login, String password, String name) {
        TypedQuery<User> query = ManagerFactory.get()
                .createNamedQuery("createUser", User.class);
        query.setParameter("login", login);
        query.setParameter("password", password);
        query.setParameter("name", name);
    }

    public List<User> findUsersLikeLogin(String pretty, Long ownerId) {

        TypedQuery<User> query = ManagerFactory.get()
                .createNamedQuery("findUsersLikeLogin", User.class);
        query.setParameter("pretty", pretty);
        query.setParameter("ownerId", ownerId);
        return query.getResultList();

//       ??????????????
//        return result;
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
