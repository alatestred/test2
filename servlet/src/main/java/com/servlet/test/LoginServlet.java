package com.servlet.test;

import com.servlet.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Daulet Zholtayev
 * @since 15.09.2020 - 20:08
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String psw = req.getParameter("psw");

        // FILTER
        HttpSession session = req.getSession();
        if(session.getAttribute("user") != null) {
            resp.sendRedirect("/info");
            return;
        }

        if(login == null || psw == null){
            resp.sendError(401);
            return;
        }

        try {
            AuthService.login(login, psw, session);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("http://localhost:63342/servlet/web/front/index.html");
        }

        resp.sendRedirect("/info");
    }
}
