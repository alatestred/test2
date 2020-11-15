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

        <input id="search">
        <input type="button" value="search" onclick="search()">
        <div id="search-result">

        </div>

        <tr>
        <% List<Chat> chats = (List<Chat>) request.getAttribute("chats");%>

        <%
            for (Chat chat : chats) {
                out.println("<td><a href='/msg?chatId=" + chat.getId() + "'>" + chat.getName() + "</a></td>");
            }
        %>
        </tr>
    </div>
</div>
</body>
<footer>
    <script>
        var results = document.getElementById("search-result")
        var inputSearch = document.getElementById("search");
        function search() {
            var param = inputSearch.value;
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    var list = JSON.parse(this.responseText);
                    var text = "";
                    for (var i = 0; i < list.length; i++) {
                        text += "<div style='cursor: pointer; margin-bottom: 10px;' onclick='selectUser("+list[i].id+")'>" + list[i].login + " - " + list[i].name + "</div><br>";
                    }
                    results.innerHTML = text;
                }
            };
            xhttp.open("GET", '/rest/user/find?param='+param);
            xhttp.send();
        }

        function selectUser(id) {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    var response = JSON.parse(this.responseText);
                    if(response.status === 'OK') {
                        window.location.href = "http://localhost:8080/msg?chatId=" + response.data;
                    }
                }
            };
            xhttp.open("GET", '/rest/user/createChat?id='+id);
            xhttp.send();
        }
    </script>
</footer>
</html>
