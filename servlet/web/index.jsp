<%@ page import="com.servlet.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="index.css" rel="stylesheet" type="text/css">
    <title>Main</title>
</head>
<body>
<div class="index-box">
<%! String helloText = "Welcome"; %>
<% User user = (User) request.getSession().getAttribute("user");%>
<h2><%= helloText %>
    <%
        if (user != null) {
            out.println(user.getName());
        }
    %>
</h2>


    <a href=
<% if(user == null) {
        out.println(
                "\"/login\"" +
                " <span></span>\n" +
                "        <span></span>\n" +
                "        <span></span>\n" +
                "        <span></span>\n" +
                "Login</a>");
        out.println("<a href=\"/registration\">" +
                " <span></span>\n" +
                "        <span></span>\n" +
                "        <span></span>\n" +
                "        <span></span>\n" +
                "Regiser</a>");
} else {
    out.println(
            "<a href=\"/logout\">" +
            " <span></span>\n" +
            "        <span></span>\n" +
            "        <span></span>\n" +
            "        <span></span>" +
            "Logout</a>\n");
}
%>>
    </div>
</div>
</body>
</html>
