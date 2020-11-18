package com.servlet.servlet;

import com.servlet.service.AuthService;

import javax.servlet.RequestDispatcher;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.doPost(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String login = req.getParameter("login");
        String psw = req.getParameter("psw");

        // FILTER
        HttpSession session = req.getSession();
        if(session.getAttribute("user") != null) {
            resp.sendRedirect("/");
            return;
        }

        if(login == null || psw == null){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(req, resp);
            return;
        }

        try {
            AuthService authService = new AuthService();
            authService.login(login, psw, req);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(req, resp);
        }

        resp.sendRedirect("/info");
    }
}
