package com.servlet.servlet;

import com.servlet.domain.Message;
import com.servlet.service.MessagesService;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet
public class MessagesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
//        String id = req.getParameter("id");
//        String author = req.getParameter("author");
//        String messageIn = req.getParameter("message");
//        System.out.println(messageIn);
//        MessagesService.setMessage(id, author, messageIn);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
//        String pathInfo = req.getPathInfo();
//        String[] split = pathInfo.split("/");
//        String sId = split[1];
//        Long id = Long.valueOf(sId);
        String pId = req.getParameter("id");
        Long id = Long.valueOf(pId);

        try {
            List<Message> messages = MessagesService.getMessages(id);
            req.setAttribute("messages", messages);
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

