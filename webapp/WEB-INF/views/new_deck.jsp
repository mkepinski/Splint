<%--
  Created by IntelliJ IDEA.
  User: michalkepinski
  Date: 22/02/2021
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>New deck</title>
</head>
<body>
<%@ include file="header.jsp"%>
<form method="post">
    <h1>Create new deck</h1>
    Deck name
    <br>
    <input type="text" name="deckName">
    <br>
    <input type="submit" value="Create deck">
</form>

</body>
</html>
