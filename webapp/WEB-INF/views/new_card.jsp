<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>New card</title>
</head>
<body>
<%@ include file="header.jsp"%>
<h1>Add new card to your deck</h1>
<form method="post">
    Back:
    <input type="text" name="back">
    <br>
    Front:
    <input type="text" name="front">
    <br>
    <br>
    <input type="submit" value="Add card"/>
</form>

<a href="<c:url value="http://localhost:8080/deck/finish"/>"/>Finish deck
</body>
</html>
