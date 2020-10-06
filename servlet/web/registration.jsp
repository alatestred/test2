
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="registration.css" rel="stylesheet" type="text/css">

    <title>Login</title>

</head>

<body>
<div class="dws-wrapper">



    <div class="dws-form">

        <input type="radio" name="tabs" id="tab-2">
        <label class="tab" for="tab-2" title="Вкладка 2">Регистрация</label>

        <form id="form-2" class="tab-form" action="/registration" method="post">
            <div class="box-input">
                <input class="input" id="login" name="login"  type="text" required/>
                <label>Введите логин</label>
            </div>
            <div class="box-input">
                <input class="input" id="name" name="name"  type="text" required/>
                <label>Введите Имя</label>
            </div>
            <div class="box-input">
                <input class="input" id="password1" name="psw"  type="password" required/>
                <label>Введите пароль</label>
            </div>

            <input  class="button" type="submit" value="РЕГИСТРАЦИЯ">

        </form>


        <% if (request.getAttribute("error") != null) {out.println(request.getAttribute("error"));}%>
    </div>
</div>
</body>
</html>
