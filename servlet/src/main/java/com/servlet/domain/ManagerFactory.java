package com.servlet.domain;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * @author Daulet Zholtayev
 * @since 18.11.2020 - 20:48
 */
public class ManagerFactory {

    private static EntityManager em;

    @PostConstruct
    public synchronized static EntityManager get() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory("lesson").createEntityManager();
        }
        return em;
    }
}
