<%--
  Created by IntelliJ IDEA.
  Data.User: USUARIO
  Date: 11/28/2023
  Time: 6:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LogIn</title>
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

        .submit-btn {
            background-color: #007bff;
            color: #fff;
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }

        .submit-btn:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>
<div class="form-container">
    <h2>Login</h2>
    <form action="/login" method="post">
        <input name="mail" type="email" placeholder="Email" class="input-control">
        <input name="password" type="password" placeholder="Password" class="input-control">
        <input type="submit" value="Login" class="submit-btn">
    </form>
</div>
</body>
</html>
