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
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public Chat findExistsChatId(Long currentId, Long userId) {
        TypedQuery<Chat> query = ManagerFactory.get()
                .createNamedQuery("findExistsChatId", Chat.class);
        query.setParameter("currentId", currentId);
        query.setParameter("userId", userId);
        return query.getSingleResult();
    }

    public void createChatUser(Long userId, Long chatId) {
        TypedQuery<Chat> query = ManagerFactory.get()
                .createNamedQuery("findExistsChatId", Chat.class);
        query.setParameter("userId", userId);
//       ???
        query.setParameter("chatId", chatId);
    }
}
