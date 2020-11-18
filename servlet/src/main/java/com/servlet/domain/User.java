package com.servlet.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Daulet Zholtayev
 * @since 15.09.2020 - 20:16
 */
@Table(name = "users")
@Entity
// JPQL - java persistence query language
@NamedQueries(value = {
        @NamedQuery(name = "findByLogin", query = "select u from User u where u.login=:login"),
})
public class User {

    @Id
    @Column
    private long id;

    @NotNull
    @Column(name = "login", unique = true, length = 300)
    private String login;

    @NotNull
    @Column(name = "password", nullable = false, length = 300)
    private String psw;

    @Column(length = 200)
    private String name;


    public User(long id, String login, String name) {
       this(id, login, name, null);
    }

    public User(Long id, String login, String name, String psw) {
        this.id = id;
        this.login = login;
        this.psw = psw;
        this.name = name;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getName() {
        return name;
    }
}
