<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
    <style>
        body {
            background-color: orange;
        }
        .center {
            text-align: center;
        }
        .registration-container {
            display: inline-block;
            margin-top: 50px;
            padding: 20px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }
        .moving-text {
            white-space: nowrap;
            overflow: hidden;
            direction: rtl; /* Set text direction to right-to-left */
            animation: moveLeftToRight 150s linear infinite;
        }

        @keyframes moveLeftToRight {
            0% {
                right: 100%;
            }
            100% {
                right: -100%;
            }
        }
    </style>
</head>
<body>
    <div class="center">
        <h2>User Registration</h2>
    </div>
    <div class="center registration-container">
        <form action="/registration" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br>

            <label for="mobile">Mobile:</label>
            <input type="text" id="mobile" name="mobile" required><br>

            <input type="submit" value="Register"><br>
        </form>
    </div>
    <div class="center">
        <a href="login">Go back to Login</a><br>
    </div>
    <div class="center">
        <p class="moving-text">The last date of registration of 10-Aug-2023</p>
    </div>
</body>
</html>
