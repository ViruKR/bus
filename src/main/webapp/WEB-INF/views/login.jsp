<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Login</title>
    <style>
        body {
            background-color: orange;
        }
        .center {
            text-align: center;
        }
        .login-container {
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
            position: relative;
            animation: moveRightToLeft 150s linear infinite;
        }

        @keyframes moveRightToLeft {
            0% {
                left: 100%;
            }
            100% {
                left: -100%;
            }
        }
    </style>
</head>
<body>
    <div class="center">
        <h2>User Login</h2>
    </div>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <div class="center">
    <div class="center login-container">
        <form action="/login" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>

            <input type="submit" value="Login"><br>
        </div>
        </form>
    </div>
    <div class="center">
        <a href="registration">Go back to Registration</a><br>
    </div>
    <div class="center">
        <p class="moving-text">The last date of registration of 10-Aug-2023</p>
    </div>
</body>
</html>
