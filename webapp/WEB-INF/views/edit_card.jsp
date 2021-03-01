<%--
  Created by IntelliJ IDEA.
  User: michalkepinski
  Date: 24/02/2021
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="footer" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit card</title>
</head>
<body>
<%@ include file="header.jsp"%>
    <form method="post" action="/card/edit">
        Back:
        <input type="text" name="back" value="${card.back}">
        <br>
        Front:
        <input type="text" name="front" value="${card.front}">
        <br>
        <input type="submit" value="Change card">
    </form>
</body>
</html>
