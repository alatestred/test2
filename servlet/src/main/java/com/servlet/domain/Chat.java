package com.servlet.domain;

import com.servlet.domain.enams.ChatType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "chat")
@NamedQueries(value = {
        @NamedQuery(name = "findExistsChatId", query = "select count(c), uc.chatId " +
                "from UserChat uc join Chat c on c.id = uc.chatId " +
                "where c.type='SINGLE' and (uc.userId=:currentId or uc.userId=:userId) " +
                "group by uc.chatId")
})
public class Chat implements Serializable {

    @Id
    @Column
    private Long id;

    @NotNull
    @Column(nullable = false, length = 50)
    private ChatType type;

    @Column (length = 300)
    private String name;

    public Chat() {
    }

    public Chat(ChatType type, String name) {
        this(null, type, name);
    }

    public Chat(Long id, ChatType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChatType getType() {
        return type;
    }

    public void setType(ChatType type) {
        this.type = type;
    }

}
