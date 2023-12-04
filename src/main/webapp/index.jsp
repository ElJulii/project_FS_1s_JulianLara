<html>
<head>
    <title>GamesFull</title>
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

        h2 {
            color: #333;
            text-align: center;
        }

        .cont {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 300px;
            text-align: center;
        }

        p {
            margin: 10px 0;
            color: #555;
        }

        form {
            display: inline-block;
            margin-top: 10px;
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

<div class="cont">
    <h2>WELCOME TO GAMES FULL OFFICIAL STORE!</h2>
    <p>No account?</p>
    <form action="/register">
        <input type="submit" value="Register">
    </form>
    <P>I already have an account</P>
    <form action="/login">
        <input type="submit" value="Login">
    </form>
</div>

</body>
</html>
