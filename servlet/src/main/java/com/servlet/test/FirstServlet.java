package com.servlet.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author Daulet Zholtayev
 * @since 29.08.2020 - 18:11
 */
public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // gson
        // jackson

//        JsonObject object = new JsonObject();
//        object.addProperty("name", req.getParameter("param"));

        Map<String, String> map = new HashMap<String, String>();
        map.put("userName", req.getParameter("param"));


        // download POSTMAN
        // req.getCookies() get and edit
        String name = req.getParameter("userName");
        Cookie cook = new Cookie("userName", name);
        resp.addCookie(cook);

        Cookie sessionId = new Cookie("sessionId", req.getRemoteAddr());
        resp.addCookie(sessionId);

        // req.getHeaderNames() req.getHeader()

        Enumeration headerName = req.getHeaderNames();
        while (headerName.hasMoreElements()) {
            System.out.println(headerName.nextElement());
        }
        //req.getRequestedSessionId()

        Cookie c1 = new Cookie("sessionId", req.getRequestedSessionId());
        resp.addCookie(c1);


//        resp.sendRedirect();

//        resp.sendRedirect("https://www.google.com");

//        resp.sendError();
//        resp.sendError();



        resp.getWriter().println(new ObjectMapper().writeValueAsString(map));
    }

    /**
     * TODO
     * POST Requests
     * AUTH
     * SESSION
     */
}
