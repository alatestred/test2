package com.servlet.service;

import com.servlet.domain.User;

import javax.servlet.http.HttpSession;

/**
 * @author Daulet Zholtayev
 * @since 25.09.2020 - 20:21
 */
public class AuthService {

    public static void login(String login, String psw, HttpSession session) throws Exception {
        User user = UserService.findUser(login);
        if (user == null) {
            throw new Exception("User not found");
        }

        if (!user.getPsw().equals(psw)) {
            throw new Exception("Invalid password");
        }

        session.setAttribute("user", user);
    }

    public static void logout(HttpSession session) {
        session.invalidate();
    }
}
