package com.servlet.repositories;

import com.servlet.domain.Chat;
import com.servlet.domain.ManagerFactory;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@LocalBean
public class ChatRepository {
    public List<Chat> getChats(Long userId) {
        TypedQuery<Chat> query = ManagerFactory.get()
                .createNamedQuery("getChats", Chat.class);
        query.setParameter("user_id", userId);
        return query.getResultList();
    }
}
