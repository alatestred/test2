<%@ page import="com.servlet.domain.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.10.2020
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>messages</title>
    <link href="messages.css" rel="stylesheet" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
    <div class="chat-field">
        <div class="messages">
            <div class="messages-content" id="messages">
            </div>

            <div class="message_input">
                <input id="message-text" class="message-form_inp" name="message" type="text" autocomplete="off" required
                       placeholder="Enter your message">
                <input type="button" class="message-form_sub" value="Send" onclick="sendMessages()">
            </div>
        </div>
    </div>
</body>
<footer>
    <script>
        <%List<Message> messages = (List<Message>) request.getAttribute("messages");%>
        let messagesList = <%=new ObjectMapper().writeValueAsString(messages)%>;
        let message = document.getElementById("message-text");
        let messages = document.getElementById("messages");

        init();

        let webSocket = new WebSocket("ws://localhost:8080/wsChat")

        webSocket.onmessage = function (event) {
            console.log("WebSocket message received:", event);
            let obj = JSON.parse(event.data);
            messagesList.push(obj);
            init();
        };

        function sendMessages() {
            let obj = {
                chatId: <%=request.getAttribute("chatId")%>,
                message: message.value
            }
            webSocket.send(JSON.stringify(obj));
            message.value = '';
        }

        function init() {
            let msgs = '';
            for (let i = 0; i < messagesList.length; i++) {
                msgs += messagesList[i].author + '-' + messagesList[i].date + '<br>' +
                    messagesList[i].message + '<br>';
            }
            messages.innerHTML = msgs;
        }
    </script>
</footer>
</html>
