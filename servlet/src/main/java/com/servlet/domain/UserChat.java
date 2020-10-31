package com.servlet.domain;

public class UserChat {
    private long id;
    private long userId;
    private long chatId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public UserChat(long id, long userId, long chatId) {
        this.id = id;
        this.userId = userId;
        this.chatId = chatId;
    }
}
