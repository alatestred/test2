package com.servlet.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.servlet.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Daulet Zholtayev
 * @since 15.09.2020 - 20:29
 */
public class MainInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null) {
            resp.sendError(401);
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("<html><head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                " </head><body><h1>Добро пожаловать, " + user.getLogin() + "!</h1>" +
                "<form action = \"http://localhost:8080/logout\">" +
                "<button type=\"submit\" class=\"float\">Logout</button></form>" +
                "</body></html>");
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println(builder.toString());
    }
}
