package com.servlet.repositories;

import com.servlet.domain.Chat;
import com.servlet.domain.ManagerFactory;
import com.servlet.domain.UserChat;
import com.servlet.domain.enams.ChatType;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class ChatRepository {

    public List<Chat> getChats(Long userId) {
        Query query = ManagerFactory.get()
                .createNativeQuery("select c.id, c.type, case when c.type = 'SINGLE' then " +
                        "(select name from users as u " +
                        "inner join user_chat u2 on u.id = u2.user_id where u.id != uc.user_id " +
                        "and u2.chat_id = c.id) " +
                        "when c.type = 'GROUP' then c.name end " +
                        "from chat as c inner join user_chat uc on c.id = uc.chat_id where uc.user_id =:user_id");
        query.setParameter("user_id", userId);

        List<Chat> chats = new ArrayList<>();
        for (Object o : query.getResultList()) {
            Object[] data = (Object[]) o;
            Integer id = (Integer) data[0];
            chats.add(new Chat(id.longValue(), ChatType.valueOf((String) data[1]), (String) data[2]));
        }
        return chats;
    }

    public Long findExistsSingleChatId(Long currentId, Long userId) {
        Query query = ManagerFactory.get().createNamedQuery("findExistsChatId");
        query.setParameter("currentId", currentId);
        query.setParameter("userId", userId);
        for (Object o : query.getResultList()) {
            Object[] data = (Object[]) o;
            int count = (int) data[0];
            Integer chatId = (Integer) data[1];
            if(count  == 2) {
                return chatId.longValue();
            }
        }
        return null;
    }

    public void addUserToChat(Long userId, Long chatId) {
        UserChat userChat = new UserChat(userId, chatId);
        ManagerFactory.get().merge(userChat);
    }

    public void createAndFlush(Chat chat) {
        ManagerFactory.get().persist(chat);
        ManagerFactory.get().flush();
    }

    public void update(Chat chat) {
        ManagerFactory.get().merge(chat);
    }
}
