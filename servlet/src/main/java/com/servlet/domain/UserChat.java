package com.servlet.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "user_chat")
public class UserChat implements Serializable {

    @Id
    @NotNull
    @Column(name = "user_id", nullable = false)
    private long userId;

    @Id
    @NotNull
    @Column(name = "chat_id", nullable = false)
    private long chatId;

    public UserChat() {
    }

    public UserChat(long userId, long chatId) {
        this.userId = userId;
        this.chatId = chatId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }
}
