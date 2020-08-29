package com.servlet.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        map.put("name", req.getParameter("param"));


        // download POSTMAN
        // req.getCookies() get and edit
        // req.getHeaderNames() req.getHeader()
        //req.getRequestedSessionId()

//        resp.sendRedirect();
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
