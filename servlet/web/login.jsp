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

    <form id="form" class="tab-form">

        <div class="user-box">
            <input type="text" name="login" id="login" autocomplete="off" required>
            <label>Login</label>
        </div>

        <div class="user-box">
            <input type="password" name="psw" id="psw" required>
            <label>Password</label>
        </div>

        <button type="button" class="float" onclick="tryLogin()">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <b>Log In</b>
        </button>
    </form>

    <h5 id="errors" style="color: white">
    </h5>

</div>
</body>
<footer>
    <script>
        function tryLogin() {
            var login = document.getElementById("login").value;
            var psw = document.getElementById("psw").value;
            var req = new XMLHttpRequest();
            req.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    window.location.href = "http://localhost:8080/info";
                } else {
                    let data = JSON.parse(this.responseText);
                    console.log('error: ' + data)
                    var errors = document.getElementById("errors");
                    errors.innerHTML = data.msg;
                }
            };
            req.open("GET", '/rest/login?login=' + login + "&psw=" + psw);
            req.send();
        }
    </script>
</footer>
</html>
