<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Study card</title>
</head>
<body>
<%@ include file="header.jsp"%>
<form method="get" action="/deck/studyCard">
    <input type="text" name="back" value="${card.back}">
    <input type="text" name="front">
    <input type="submit" value="Check answer">
</form>
</body>
</html>
