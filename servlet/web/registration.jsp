
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="registration.css" rel="stylesheet" type="text/css">

    <title>Login</title>

</head>

<body>




    <div class="registration-box">

        <h2>Sign Up</h2>

        <form id="form-2" class="tab-form" action="/registration" method="post">

            <div class="user-box">
                <input class="input" id="login" name="login"  type="text" autocomplete="off" required/>
                <label>Username</label>
            </div>
            <div class="user-box">
                <input class="input" id="name" name="name"  type="text" autocomplete="off" required/>
                <label>Name</label>
            </div>
            <div class="user-box">
                <input class="input" id="password1" name="psw"  type="password" required/>
                <label>Password</label>
            </div>

            <button type="submit" class="float">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <b>Sign Up</b>
            </button>

        </form>


        <h5><% if (request.getAttribute("error") != null) {out.println(request.getAttribute("error"));}%></h5>
    </div>

</body>
</html>
