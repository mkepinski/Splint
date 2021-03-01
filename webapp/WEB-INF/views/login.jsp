<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>

<form:form method="post">
    <h1>Log in</h1>
    Login
    <br>
    <input type="text" name="login">
    <br>
    Password
    <br>
    <input type="password" name="password">
    <br>
    Stay logged
    <input type="checkbox" name="stayLogged" checked>
    <br>
    <input type="submit" value="Log in">
</form:form>
<form action="http://localhost:8080/account/new">
    <input type="submit" value="New here? Create an account!">
</form>
</body>
</html>
