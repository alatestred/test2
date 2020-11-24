package com.servlet.repositories;

import com.servlet.domain.ManagerFactory;
import com.servlet.domain.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
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
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public void createUser(String login, String password, String name) {
        User user = new User(null, login, name, password);
        ManagerFactory.get().persist(user);
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
