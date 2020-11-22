package com.servlet.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servlet.domain.Message;
import com.servlet.domain.User;
import com.servlet.service.MessagesService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class MessagesResource {
    @GET
    @Path("/messages")
    public void getMessages(@Context HttpServletRequest req, @Context HttpServletResponse resp) {

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
//
    @POST
    @Path("/send")
    public void sendMessages(@Context HttpServletRequest req, @Context HttpServletResponse resp) {
        String messageIn = req.getParameter("message");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String chatId = req.getParameter("chatId");
        try {
            MessagesService.setMessage( messageIn, Long.valueOf(chatId), user.getId(), null);
            String pId = req.getParameter("chatId");
            Long id = Long.valueOf(pId);
            List<Message> messages = MessagesService.getMessages(id);
            resp.getWriter().println(new ObjectMapper().writeValueAsString(messages));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
