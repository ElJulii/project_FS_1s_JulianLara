<%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 12/3/2023
  Time: 9:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EditUser</title>
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
            max-width: 400px;
            margin: 20px auto;
        }

        .edit-password {
            text-align: center;
        }

        h1 {
            font-size: 24px;
            color: #333;
            margin-bottom: 10px;
        }

        h2 {
            font-size: 18px;
            color: #555;
            margin-bottom: 20px;
        }

        .input-control {
            margin-bottom: 20px;
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
    <form action="/edit" method="post">
        <div class="edit-password">
            <h1>Edit Password:</h1>
            <h2>Write the new password</h2>
            <input name="password" type="password" placeholder="Enter new password" class="input-control">
            <input type="submit" value="Update Password" class="submit-btn">
        </div>
    </form>
</div>
</body>
</html>
