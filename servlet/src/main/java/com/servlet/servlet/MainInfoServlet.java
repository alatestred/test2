package com.servlet.servlet;

import com.servlet.domain.Chat;
import com.servlet.domain.User;
import com.servlet.service.ChatService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author Daulet Zholtayev
 * @since 15.09.2020 - 20:29
 */
public class MainInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(req, resp);
            return;
        }

        try {
            List<Chat> chats = ChatService.getChats(user.getId());
            req.setAttribute("chats", chats);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("main.jsp");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
