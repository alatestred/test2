package com.servlet.service;

import com.servlet.domain.User;
import com.servlet.repositories.UserRepository;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Daulet Zholtayev
 * @since 25.09.2020 - 20:21
 */
public class AuthService {

    public void login(String login, String psw, HttpServletRequest request) throws Exception {
        UserRepository userRepository = new UserRepository();
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new Exception("User not found");
        }

        if (!user.getPsw().equals(psw)) {
            throw new Exception("Invalid password");
        }

        request.getSession().setAttribute("user", user);
        request.setAttribute("userName", user.getName());
    }

    public static void logout(HttpSession session) {
        session.invalidate();
    }
}
