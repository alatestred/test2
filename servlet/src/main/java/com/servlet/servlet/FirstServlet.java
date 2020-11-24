package com.servlet.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import java.io.IOException;
import java.util.*;

/**
 * @author Daulet Zholtayev
 * @since 29.08.2020 - 18:11
 */

public class FirstServlet extends HttpServlet {


    // TODO
    // home task 15.09.2020
    // сделать servlet logout для выхода из сессий
    // сделать registration servlet

    //23.09
    //регистрация хтмл
    //добавить имя в регистрации

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Map<String, String> map = new HashMap<String, String>();
        map.put("userName", req.getParameter("param"));


        // download POSTMAN
        // req.getCookies() get and edit
        String name = req.getParameter("param");
        Cookie cook = new Cookie("userName", name);
        resp.addCookie(cook);

        Cookie sessionId = new Cookie("sessionId", req.getRemoteAddr());
        resp.addCookie(sessionId);

        // req.getHeaderNames() req.getHeader()

        if (req.getHeader("testtoken") != null) {
            map.put("token", req.getHeader("testtoken"));
        }

        Enumeration<String> headers = req.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headName = headers.nextElement();
            System.out.println(headName + ": " + req.getHeader(headName));
        }
        //req.getRequestedSessionId()

        Cookie c1 = new Cookie("sessionId", req.getRequestedSessionId());
        resp.addCookie(c1);


        resp.getWriter().println(new ObjectMapper().writeValueAsString(map));
    }

    /**
     * TODO
     * POST Requests
     * AUTH
     * SESSION
     */
}
