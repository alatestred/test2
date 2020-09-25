package com.servlet.domain;

/**
 * @author Daulet Zholtayev
 * @since 15.09.2020 - 20:16
 */
public class User {

    private String login;
    private String psw;
    private String name;


    public User(String login, String name, String psw) {
        this.login = login;
        this.psw = psw;
        this.name = name;
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
