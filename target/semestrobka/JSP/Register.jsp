<%--
  Created by IntelliJ IDEA.
  Data.User: USUARIO
  Date: 11/28/2023
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
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
            width: 300px;
            text-align: center;
        }

        .input-control {
            margin: 10px 0;
            padding: 8px;
            width: 100%;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>REGISTER</h2>
    <form action="/register" method="post">
        <input name="name" placeholder="Name" class="input-control">
        <input name="lastname" placeholder="Lastname" class="input-control">
        <input name="mail" type="email" placeholder="Email" class="input-control">
        <input name="password" type="password" placeholder="Password" class="input-control">
        <input type="submit" value="Register">
    </form>
</div>
</body>
</html>
