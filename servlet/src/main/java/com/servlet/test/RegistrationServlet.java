package com.servlet.test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.servlet.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        BufferedReader reader = req.getReader();
//        StringBuilder builder = new StringBuilder();
//        String line = "";
//        while ((line=reader.readLine()) != null) {
//            builder.append(line);
//            System.out.println(line);
//        }
//        JsonParser parser = new JsonParser();
////        JsonObject jObj = (JsonObject) parser.parse(builder.toString());
//
//        System.out.println(jObj.get("login").getAsString());
//        System.out.println(jObj.get("psw").getAsString());
//        System.out.println(jObj.get("name").getAsString());
        System.out.println(req.getParameter("login" ));
        System.out.println(req.getParameter("name" ));
        System.out.println(req.getParameter("psw" ));

        LoginServlet.USERS.add(new User(req.getParameter("login" ), req.getParameter("name" ),
                req.getParameter("psw" )));

//        LoginServlet.USERS.add(new User (jObj.get("login").getAsString(), jObj.get("name").getAsString(),
//                jObj.get("psw").getAsString()));
        resp.sendRedirect("http://localhost:63342/servlet/web/front/index.html");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
