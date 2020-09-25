package com.servlet.test;

import com.servlet.domain.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daulet Zholtayev
 * @since 15.09.2020 - 20:08
 */
public class LoginServlet extends HttpServlet {

    public static List<User> USERS = new ArrayList<>(); // {new User("test", "123"), new User("java", "123qwe123")};
    static {
        USERS.add(new User("test", "test", "123"));
        USERS.add(new User("java", "java", "123qwe123"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String psw = req.getParameter("psw");

        HttpSession session = req.getSession();
        if(session.getAttribute("user") != null) {
            resp.sendRedirect("/info");
            return;
        }

        if(login == null || psw == null){
            resp.sendError(401);
            return;
        }

        User user = findUser(login);
        if(user == null) {
            resp.sendError(404);
            return;
        }

        if(!user.getPsw().equals(psw)) {
            resp.sendError(406);
            return;
        }

        session.setAttribute("user", user);
        resp.sendRedirect("/info");
    }

    private User findUser(String login) {
        for (User user : USERS) {
            if(user.getLogin().equals(login)){
                return user;
            }
        }
        return null;
    }
}
