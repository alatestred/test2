package com.servlet.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.servlet.domain.Message;
import com.servlet.domain.User;
import com.servlet.service.MessagesService;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet
public class MessagesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String messageIn = req.getParameter("message");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String chatId = req.getParameter("chatId");
        try {
            MessagesService.setMessage( messageIn, Long.valueOf(chatId), user.getId());
            String pId = req.getParameter("chatId");
            Long id = Long.valueOf(pId);
            List<Message> messages = MessagesService.getMessages(id);
            resp.getWriter().println(new ObjectMapper().writeValueAsString(messages));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
//        String pathInfo = req.getPathInfo();
//        String[] split = pathInfo.split("/");
//        String sId = split[1];
//        Long id = Long.valueOf(sId);
        String pId = req.getParameter("chatId");
        Long id = Long.valueOf(pId);

        try {
            List<Message> messages = MessagesService.getMessages(id);
            req.setAttribute("messages", messages);
            req.setAttribute("chatId", id);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("messages.jsp");
            requestDispatcher.forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

