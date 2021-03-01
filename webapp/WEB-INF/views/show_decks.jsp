<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Decks</title>
</head>
<body>
<%@ include file="header.jsp"%>
<p><h1>Your decks</h1></p>
<table>
    <c:forEach var="deck" items="${deckList}">
        <tr>
            <td>${deck.deckName}</td>
            <td><a href="<c:url value="http://localhost:8080/deck/show/deck/${deck.deckName}"/>"/>Show deck</td>
            <td><a href="<c:url value="http://localhost:8080/deck/studyDeck/${deck.deckName}"/>"/>Study deck</td>
            <td><a href="<c:url value="http://localhost:8080/deck/delete/${deck.deckName}"/>"/>Delete deck</td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="<c:url value="http://localhost:8080/deck/newDeck"/>"/>Create new deck
</body>
</html>
