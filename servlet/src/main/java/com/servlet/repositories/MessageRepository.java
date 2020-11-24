package com.servlet.repositories;

import com.servlet.domain.ManagerFactory;
import com.servlet.domain.Message;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@LocalBean
public class MessageRepository {

    public List<Long> getUsersIdOfChat(long chatId) {
        TypedQuery<Long> query = ManagerFactory.get()
                .createNamedQuery("getUsersIdOfChat", Long.class);
        query.setParameter("chatId", chatId);
        return query.getResultList();
    }

    public List <Message> getMessages(long chatId) {
        TypedQuery<Message> query = ManagerFactory.get()
                .createNamedQuery("getMessages", Message.class);
        query.setParameter("chatId", chatId);
        return query.getResultList();
    }

    public void setMessages(String messageIn, long chatId, long author, String login) {
        TypedQuery<Message> query = ManagerFactory.get()
                .createNamedQuery("setMessages", Message.class);
        query.setParameter("messageIn", messageIn);
        query.setParameter("chatId", chatId);
        query.setParameter("author", author);
//        ???
        query.setParameter("author", login);
    }

}
