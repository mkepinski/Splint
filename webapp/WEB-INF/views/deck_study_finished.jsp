<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Deck finished!</title>
</head>
<body>
<%@ include file="header.jsp"%>
<h2>Congratulations, you finished your deck!</h2>
<h3>What next?</h3>
<a href="<c:url value="http://localhost:8080/deck/studyDeck/${deck.deckName}"/>"/>Study again
<br>
<a href="<c:url value="http://localhost:8080/deck/show/decks"/>"/>Finish studying
</body>
</html>
