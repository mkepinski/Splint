<%--
  Created by IntelliJ IDEA.
  User: michalkepinski
  Date: 21/02/2021
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>New account form</title>
</head>
<body>

<form:form method="post" modelAttribute="user">
    <h1>Create new account</h1>
    Login
    <br>
    <form:errors path="login" cssClass="error" element="div"/>
    <form:input path="login"></form:input>
    <br>
    Password
    <br>
    <form:errors path="password" cssClass="error" element="div"/>
    <form:password path="password"></form:password>
    <br>
    Email address
    <br>
    <form:errors path="email" cssClass="error" element="div"/>
    <form:input path="email"></form:input>
    <br>
    <input type="submit" value="create account">
</form:form>
<form action="http://localhost:8080/account/login">
    <input type="submit" value="Already have an account? Log in!">
</form>
</body>
</html>
