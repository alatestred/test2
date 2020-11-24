<%@ page import="com.servlet.domain.Chat" %>
<%@ page import="java.util.List" %>
<%@ page import="com.servlet.domain.User" %>

<%--
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
    <link href="main.css" rel="stylesheet" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="wrap">

    <div class="srch">
        <input id="search" type="text" required>


        <input type="button" class="searchButton" onclick="search()">
        <div id="search-result">
        </div>
    </div>
</div>
<ul id="friend-list">

    <div class="search-result">
        <% List<Chat> chats = (List<Chat>) request.getAttribute("chats");%>

        <%
            for (Chat chat : chats) {
                out.println("<li class = \"friend\"><a href='/msg?chatId=" + chat.getId() + "'>" + "<div class='name'>"
                        + chat.getName() + "</div>"
                        + "</a></li>");
            }
        %>
    </div>
</ul>

</body>
<footer>
    <script>
        let results = document.getElementById("search-result")
        let inputSearch = document.getElementById("search");

        inputSearch.addEventListener('keyup', function (event) {
            hinter(event)
        });
        window.hinterXHR = new XMLHttpRequest();

        function hinter(event) {

            let input = event.target;

            let param = inputSearch.value;

            const min_char = 0;
            if (input.value.length < min_char) {
                return;
            } else {
                window.hinterXHR.abort();
                window.hinterXHR.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        search.addEventListener
                        let list = JSON.parse(this.responseText);
                        let text = "";
                        for (let i = 0; i < list.length; i++) {
                            text += "<div style='cursor: pointer; margin-bottom: 10px;' onclick='selectUser(" + list[i].id + ")'>" + list[i].login + " - " + list[i].name + "</div><br>";
                        }
                        results.innerHTML = text;
                        console.log('text: ' + text)
                    }
                };
            }

            console.log('param: ' + param);
            <%User user = (User) request.getSession().getAttribute("user");%>
            let uLogin = '<%=user.getLogin()%>';
            console.log('login: ' + uLogin);
            window.hinterXHR.open("GET", '/rest/user/find?param=' + param + "&login=" + uLogin);
            window.hinterXHR.send();
        }


        function selectUser(id) {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    var response = JSON.parse(this.responseText);
                    if (response.status === 'OK') {
                        window.location.href = "http://localhost:8080/msg?chatId=" + response.data;
                    }
                }
            };
            xhttp.open("GET", '/rest/user/createChat?id=' + id);
            xhttp.send();
        }
    </script>
</footer>
</html>
