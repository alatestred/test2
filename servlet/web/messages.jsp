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
            <form action="/msg?chatId=<%=request.getAttribute("chatId")%>" method="post" id="message-form">
                <input id="message-text" class="message-form_inp" name="message" type="text" required
                       placeholder="Enter your message">
                <input type="submit" class="message-form_sub" value="Send">
            </form>
        </div>
    </div>
</div>


</body>
</html>
