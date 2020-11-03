package com.servlet.servlet;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.servlet.service.MessagesService;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/wsChat", configurator = ChatConfigurator.class)
public class ChatServer {

    // interface
    // abstract

    static Set<Session> sessions = new HashSet<>();

    EndpointConfig config;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        Long userId = (Long) config.getUserProperties().get("userId");
        this.config = config;
        session.getUserProperties().put("userId", userId);
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(message).getAsJsonObject();
        long chatId = obj.get("chatId").getAsLong();
        String chatMessage = obj.get("message").getAsString();

        Long userId = (Long) config.getUserProperties().get("userId");

        try {
            MessagesService.setMessage( chatMessage, chatId, userId);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        for (Session local : sessions) {
            local.getBasicRemote().sendText(message);
        }
    }
}
