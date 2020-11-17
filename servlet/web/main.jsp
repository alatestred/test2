<%@ page import="com.servlet.domain.Chat" %>
<%@ page import="java.util.List" %>
<%@ page import="com.servlet.domain.User" %><%--
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
    <div class="list">
        <div class="srch">
            <input id="search" type="text" required>

            <input type="button" value="search" onclick="search()">
            <div id="search-result">
            </div>
        </div>
    </div>
</div>
<ul>

    <div class="search-result">
            <% List<Chat> chats = (List<Chat>) request.getAttribute("chats");%>

            <%
        for (Chat chat : chats) {
            out.println("<li><a href='/msg?chatId=" + chat.getId() + "'>" + chat.getName()
                    + "</a></li>");
        }
    %>
</ul>
</div>
</body>
<footer>
    <script>
        let results = document.getElementById("search-result")
        let inputSearch = document.getElementById("search");

        function search() {
            let param = inputSearch.value;
            let xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    search.addEventListener
                    let list = JSON.parse(this.responseText);
                    console.log(list);
                    let text = "";
                    for (let i = 0; i < list.length; i++) {
                        text += "<div style='cursor: pointer; margin-bottom: 10px;' onclick='selectUser(" + list[i].id + ")'>" + list[i].login + " - " + list[i].name + "</div><br>";
                    }
                    // let a = this.responseText
                    // for (let i in a)
                    // autocomplete(inputSearch, a.login);
                    results.innerHTML = text;
                    console.log('text: ' + text)
                }
            };

            console.log('param: ' + param);
            <%User user = (User) request.getSession().getAttribute("user");%>
            let uId = <%=user.getId()%>
            console.log( 'id: ' + uId);
            xhttp.open("GET", '/rest/user/find?param=' + param + "&id=" +  uId);
            xhttp.send();
        }


        // function autocomplete(inputSearch, ) {
        //
        // }


        //
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
