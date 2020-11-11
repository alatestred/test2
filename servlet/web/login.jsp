<%--
  Created by IntelliJ IDEA.
  User: zholt
  Date: 25.09.2020
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="login.css" rel="stylesheet" type="text/css">
    <title>Login</title>
</head>
<body>
<div class="login-box">

        <h2>Log In</h2>

        <form id="form" class="tab-form" action="/login">

            <div class="user-box">
                <input type="text" name="login" id="login" autocomplete="off" required>
                <label>Login</label>
            </div>

            <div class="user-box">
                <input type="password" name="psw" id="psw" required>
                <label>Password</label>
            </div>

            <!--            <button type="submit" class="float" onclick="testlogin()">Login</button>-->
            <button type="submit" class="float">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <b>Log In</b>
            </button>
        </form>

    <h5>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
                out.println("<span>" + error + "</span>");
            }
        %>
    </h5>

</div>
</body>
<%--<%@ include file="footer.jsp"%>--%>
</html>
