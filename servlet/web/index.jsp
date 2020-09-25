<%@ page import="com.servlet.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<%! String helloText = "Hello"; %>
<% User user = (User) request.getSession().getAttribute("user");%>
<h1><%= helloText %>
    <%
        if (user != null) {
            out.println(user.getName());
        }
    %>
</h1>
<% if(user == null) {
        out.println("<a href=\"/login\">Login</a><a href=\"/registration\">Registration</a>");
} else {
    out.println("<a href=\"/logout\">Logout</a>");
}
%>

</body>

<%@ include file="footer.jsp"%>
</html>
