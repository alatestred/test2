package com.servlet.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "messages")
@NamedQueries(value = {
        @NamedQuery(name = "getUsersIdOfChat", query = "select uc.userId as user_id from UserChat as uc" +
                " where uc.chatId=:chatId"),
})
@NamedNativeQueries(value = {
        @NamedNativeQuery(name = "getMessages", query = "select m.id, m.message, m.date, u.name  from messages m" +
                " left join users as u on u.id=m.author where m.chat_id =:chatId" +
                " order by m.date asc"),
        @NamedNativeQuery(name = "setMessages", query = "insert into messages " +
                "(message, date, chat_id, author) values (:messageIn , NOW(), :chatId, :author )")
})
public class Message implements Serializable {
    @Id
    @Column
    private long id;
    @Column
    private String message;
    @Column
    private Date date;
    @NotNull
    @Column(name = "chat_id", nullable = false)
    private long chatId;
    @NotNull
    @Column(nullable = false)
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }
}
