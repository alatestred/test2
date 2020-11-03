package com.servlet.servlet;

import com.servlet.domain.User;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @author Daulet Zholtayev
 * @since 03.11.2020 - 20:01
 */
public class ChatConfigurator extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        HttpSession httpSession = (HttpSession)request.getHttpSession();
        User user = (User)httpSession.getAttribute("user");
        if(user == null) {
            return;
        }
        sec.getUserProperties().put("userId", user.getId());
        sec.getUserProperties().put("login", user.getLogin());
    }
}
