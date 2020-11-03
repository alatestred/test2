<%@ page import="com.servlet.domain.Message" %>
<%@ page import="java.util.List" %>
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
</head>
<body>
<div class="chat-field">
    <div class="messages">
        <div class="messages-content" id="messages">

            <%List<Message> messages = (List<Message>) request.getAttribute("messages");%>
            <%
                for (Message message : messages) {
                    out.println(message.getAuthor() + "-" + message.getDate() + "<br>" + message.getMessage() + "<br>");

                }%>


        </div>
        <div class="message_input">
            <form id="message-form">
                <input id="message-text" class="message-form_inp" name="message" type="text" required
                       placeholder="Enter your message">
                <input type="button" class="message-form_sub" value="Send" onclick="sendMessage()">
            </form>
            <script>

                //jsp servlet websocket

                var message = document.getElementById("message-text");
                var messages = document.getElementById("messages");

                var webSocket = new WebSocket("ws://localhost:8080/wsChat")

                webSocket.onmessage = function(event) {
                    console.log("WebSocket message received:", event.data);
                    var obj = JSON.parse(event.data);
                };

                function sendMessage() {
                    var obj = {
                        chatId : <%=request.getAttribute("chatId")%>,
                        message: message.value
                    }
                    webSocket.send(JSON.stringify(obj));
                    message.value = "";

                    // var xhttp = new XMLHttpRequest();
                    // xhttp.onreadystatechange = function() {
                    //     if (this.readyState == 4 && this.status == 200) {
                    //         var list = JSON.parse(this.responseText);
                    //         var text = "";
                    //         for(var i = 0; i < list.length; i++) {
                    //             text += list[i].author + "-" + new Date(list[i].date) + "<br>" + list[i].message + "<br>";
                    //         }
                    //         messages.innerHTML = text;
                    //         message.value = "";
                    //     }
                    // };
                    <%--xhttp.open("POST", '/msg?chatId=<%=request.getAttribute("chatId")%>&message=' + message.value, true);--%>
                    <%--xhttp.send();--%>
                }
            </script>
        </div>
    </div>
</div>


</body>
</html>
