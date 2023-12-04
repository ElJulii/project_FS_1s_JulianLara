<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Data.Game" %>
<%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 12/2/2023
  Time: 11:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .form-container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 80%;
            max-width: 600px;
            margin: 20px auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: #fff;
        }

        .add-game {
            margin-top: 20px;
        }

        h1 {
            font-size: 18px;
            color: #333;
            margin-bottom: 10px;
        }

        input[type="text"], input[type="submit"] {
            margin: 5px 0;
            padding: 8px;
            width: 100%;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }
        .button-container {
            display: flex;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>LIST OF GAMES</h2>
    <form action="/admin" method="post">
        <table>
            <tr>
                <th>ID</th>
                <th>Type</th>
                <th>Name</th>
                <th>Price</th>
            </tr>
            <c:forEach items="${data}" var="Game">
                <tr>
                    <td>${Game.id}</td>
                    <td>${Game.type}</td>
                    <td>${Game.namegame}</td>
                    <td>${Game.price}</td>
                </tr>
            </c:forEach>
        </table>
        <div class="add-game">
            <h1>BOSS, YOU CAN ADD ANOTHER GAME TO THE LIST, IN ORDER FOR THE USER CAN BUY IT</h1>
            <input name="type" placeholder="Type of Game">
            <input name="namegame" placeholder="Video Game">
            <input name="price" placeholder="Cost">
            <input type="submit" value="Add">
        </div>
    </form>
</div>
<hr>
<div class="button-container">
    <form action="/edit">
        <input type="submit" value="Edit User">
    </form>
    <form action="/login">
        <input type="submit" value="Log out">
    </form>
</div>
</body>
</html>
