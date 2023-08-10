<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete User</title>
</head>
<body>

<h2>Delete User</h2>

<%-- Display a form to capture user input --%>
<form action="delete" method="post">
    User ID: <input type="text" name="id">
    <input type="submit" value="Delete">
</form>

<%-- Display success or error messages --%>
<% if (request.getAttribute("successMessage") != null) { %>
    <p style="color: green;"><%= request.getAttribute("successMessage") %></p>
<% } else if (request.getAttribute("errorMessage") != null) { %>
    <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
<% } %>

</body>
</html>
