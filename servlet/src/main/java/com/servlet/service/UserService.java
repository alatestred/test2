package com.servlet.service;

import com.servlet.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Service to connect with DB
 * @author Daulet Zholtayev
 * @since 25.09.2020 - 20:06
 */
public class UserService {

    public static List<User> USERS = new ArrayList<>();

    static {
        USERS.add(new User("test", "test", "123"));
        USERS.add(new User("java", "java", "123qwe123"));
    }

    public static User createUser(String login, String psw, String name) throws Exception {
        if (login == null || psw == null || name == null) {
            throw new Exception("Invalid fields");
        }
        if (findUser(login) != null) {
            throw new Exception("Login exists");
        }

        User user = new User(login, name, psw);
        USERS.add(user);
        return user;
    }

    public static User findUser(String login) {
        for (User user : USERS) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }
}
