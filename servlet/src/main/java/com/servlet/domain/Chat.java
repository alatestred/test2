package com.servlet.domain;

import com.servlet.domain.enams.ChatType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "chat")
@NamedNativeQueries(value = {
        @NamedNativeQuery(name = "getChats", query = "select c.id, c.type, case when c.type = 'SINGLE' then " +
                "(select name from users as u " +
                "inner join user_chat u2 on u.id = u2.user_id where u.id != uc.user_id " +
                "and u2.chat_id = c.id)" +
                "when c.type = 'GROUP' then c.name end " +
                "from chat as c inner join user_chat uc on c.id = uc.chat_id where uc.user_id =:user_id"),
        @NamedNativeQuery(name = "findExistsChatId", query = "select count(*), chat_id " +
                "from user_chat u JOIN chat c on c.id = u.chat_id " +
                "where c.type='SINGLE' AND (u.user_id =:currentId OR u.user_id =:userId) " +
                "group by chat_id"),
        @NamedNativeQuery(name = "createChat", query = "insert into chat (name, type) values " +
                "(:name, :chatType.name ) RETURNING ID"),
        @NamedNativeQuery(name = "createChatUser", query = "insert into user_chat (user_id, chat_id)" +
                " values (:userID, :charId)")
})
public class Chat {

    @Id
    @Column
    private long id;

    @NotNull
    @Column(nullable = false, length = 50)
    private ChatType type;

    @Column (length = 300)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ChatType getType() {
        return type;
    }

    public void setType(ChatType type) {
        this.type = type;
    }

}
