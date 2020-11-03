package com.servlet.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@ServerEndpoint("/wsChat")
public class ChatServer {

    static Set<Session> sessions = new HashSet<>();
    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
//        session.getBasicRemote().sendText(message);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.readValue();
//        JsonParser parser = new JsonParser();
//        JsonElement parsedObj = parser.(message);
    }
}
