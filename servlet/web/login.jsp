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
    <title>Login</title>
</head>
<body>
<div class="login">
    <div class="heading">
        <h2>Sign in</h2>

        <form action="/login">

            <div class="input-group input-group-lg">
                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                <input type="text" class="form-control" placeholder="Username or email" name="login" id="login">
            </div>

            <div class="input-group input-group-lg">
                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                <input type="password" class="form-control" placeholder="Password" name="psw" id="psw">
            </div>

            <!--            <button type="submit" class="float" onclick="testlogin()">Login</button>-->
            <button type="submit" class="float">Login</button>
        </form>

        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
                out.println("<span>" + error + "</span>");
            }
        %>

    </div>
</div>
</body>
<%@ include file="footer.jsp"%>
</html>
