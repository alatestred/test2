package com.servlet.resource;

import com.servlet.domain.Chat;
import com.servlet.domain.User;
import com.servlet.service.ChatService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.util.List;

@Path("/info")
public class MainInfoResource {
    @Path("/chats")
    @GET
    public String getChats(@Context HttpServletRequest req, @Context HttpServletResponse resp) throws ServletException, IOException {
        {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            if(user == null) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
                requestDispatcher.forward(req, resp);
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
