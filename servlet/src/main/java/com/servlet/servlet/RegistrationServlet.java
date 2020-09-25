package com.servlet.servlet;

import com.servlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // DRY - Dont repeat yourself
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String psw = req.getParameter("psw");

        System.out.println(login);
        System.out.println(name);
        System.out.println(psw);

        try {
            UserService.createUser(login, psw, name);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/reg.html");
        }

        resp.sendRedirect("http://localhost:63342/servlet/web/front/index.html");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
