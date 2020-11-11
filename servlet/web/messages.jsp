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


        <%List<Message> messages = (List<Message>) request.getAttribute("messages");%>


<%--        <%!--%>
<%--            private Message message;--%>
<%--        %>--%>
<%--        <%--%>

<%--            for (Message message : messages) {--%>
<%--                String str1 = "<div class=\"messages-content\" id=\"messages\">";--%>

<%--                out.println(str1 + "<i>" +--%>
<%--                        message.getAuthor() + "</i>" +--%>
<%--                        "-" + message.getDate() + ": " + "<br>" + message.getMessage() + "</div>");--%>
<%--            }%>--%>


    </div>


    <div class="message_input">
            <input id="message-text" class="message-form_inp" name="message" type="text" autocomplete="off" required
                   placeholder="Enter your message">
            <input type="button" class="message-form_sub" value="Send" onclick="sendMessages()">

        <script>
            let messagesList = <%=new ObjectMapper().writeValueAsString(messages)%>;
            let message = document.getElementById("message-text");
            let messages = document.getElementById("messages");

            update();

            let webSocket = new WebSocket("ws://localhost:8080/wsChat")

            // for (let obj in data) {
            //     msgs +=obj.author + '-' + obj.date + '<br>' + obj.message + '<br>';
            //
            // }

            webSocket.onmessage = function (event) {
                console.log("WebSocket message received:", event);
                let obj = JSON.parse(event.data);
                messagesList.push(obj);
                update();
                // let msgs = messages.innerHTML;
                // msgs +=obj.author + '-' + new Date(obj.date) + '<br>' + obj.message + '<br>';
                // messages.innerHTML = msgs;
            };


            function sendMessages() {
                let obj = {
                    chatId: <%=request.getAttribute("chatId")%>,
                    message: message.value
                }
                webSocket.send(JSON.stringify(obj));
                message.value = '';
                console.log(obj)
            }

                function update() {
                    let msgs = '';
                    for (let i = 0; i < messagesList.length; i++) {
                        msgs +=messagesList[i].author + '-' + messagesList[i].date + '<br>' +
                            messagesList[i].message + '<br>';
                    }
                    messages.innerHTML= msgs;
                }

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

        </script>
    </div>
</div>


</body>
</html>
