<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Cards of the deck</title>
</head>
<body>
<%@ include file="header.jsp"%>
<h2>${deck.deckName} cards</h2>
<table>
    <thead>
    <tr>
            <td><h3>front</h3></td>
            <td><h3>back</h3></td>
    </tr>
    </thead>
    <c:forEach var="card" items="${cards}">
        <tr>
            <td>${card.front}</td>
            <td>${card.back}</td>
            <td><a href="<c:url value="http://localhost:8080/card/edit/${card.id}"/>"/>Edit card</td>
            <td><a href="<c:url value="http://localhost:8080/card/delete/${card.id}"/>"/>Delete card</td>
        </tr>
    </c:forEach>

    <br>
</table>
<a href="<c:url value="http://localhost:8080/deck/newCard"/>"/><strong>Add new card</strong>
<br>
<a href="<c:url value="http://localhost:8080/deck/studyDeck/${deck.deckName}"/>"/>Study deck
<br>
<a href="<c:url value="http://localhost:8080/deck/delete/${deck.deckName}"/>"/>Delete deck
</body>
</html>
