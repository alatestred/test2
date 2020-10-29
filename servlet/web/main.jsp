<%@ page import="com.servlet.domain.Chat" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.10.2020
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>main</title>
</head>
<body>
<div class="main">
    <div class="list">
        <% List<Chat> chats = (List<Chat>) request.getAttribute("chats");%>

        <%
            for (Chat chat : chats) {
                out.println("<a href='/msg?id=" + chat.getId() + "'>" + chat.getName() + "</a>");
            }
        %>
    </div>
</div>
</body>
</html>
