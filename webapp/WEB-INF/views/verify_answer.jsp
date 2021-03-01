<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Verify</title>
</head>
<body>
<%@ include file="header.jsp"%>
<h3>${message}</h3>
<a href="<c:url value="http://localhost:8080/deck/studyDeck/${deck.deckName}"/>"/>Next card
<a href="<c:url value="http://localhost:8080/deck/show/decks"/>"/>Finish studying
</body>
</html>
