<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Delete deck?</title>
</head>
<body>
<h2>Are you sure you want to delete this deck?</h2>
<br>
<a href="<c:url value="http://localhost:8080/deck/delete/confirmed/${deckName}"/>"/>Yes
<a href="<c:url value="http://localhost:8080/deck/show/decks"/>"/>No
</body>
</html>
