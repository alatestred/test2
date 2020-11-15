package com.servlet.domain.dto;

/**
 * @author Daulet Zholtayev
 * @since 15.11.2020 - 11:47
 */
public class UserDTO {

    private Long id;
    private String name;
    private String login;

    public UserDTO(Long id, String name, String login) {
        this.name = name;
        this.login = login;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}