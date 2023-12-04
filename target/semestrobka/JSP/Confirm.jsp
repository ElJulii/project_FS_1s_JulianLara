<%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 12/3/2023
  Time: 11:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm buy</title>
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
            text-align: center;
        }

        h1 {
            font-size: 24px;
            color: #333;
            margin-bottom: 20px;
        }

        .file-label {
            display: inline-block;
            position: relative;
            cursor: pointer;
            background-color: #007bff;
            color: #fff;
            padding: 10px 15px;
            border-radius: 4px;
            overflow: hidden;
        }

        .file-input {
            position: absolute;
            font-size: 100px;
            opacity: 0;
            right: 0;
            top: 0;
        }

        .file-label span {
            font-size: 16px;
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
    <h1>In order to have a better idea of your game, please upload an image.</h1>
    <form action="/confirm" method="post" enctype="multipart/form-data">
        <label for="file-input" class="file-label">
            <span>Choose a file</span>
            <input type="file" id="file-input" name="file" class="file-input">
        </label>
        <input type="submit" value="Upload Image" class="submit-btn">
    </form>
</div>
</body>
</html>
