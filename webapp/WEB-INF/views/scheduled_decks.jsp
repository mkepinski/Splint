<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Scheduled decks</title>
</head>
<body>
<%@ include file="header.jsp"%>
<p><h1>Decks for you today</h1></p>
<table>
    <c:forEach var="deck" items="${scheduledDecks}">
        <tr>
            <td>${deck.deckName}</td>
            <td><a href="<c:url value="http://localhost:8080/deck/show/deck/${deck.deckName}"/>"/>Show deck</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
