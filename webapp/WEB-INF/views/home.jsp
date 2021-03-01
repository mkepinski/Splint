<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<a href="<c:url value="http://localhost:8080/account/new"/>"/>Create new account
<br>
<a href="<c:url value="http://localhost:8080/account/login"/>"/>Log in
<br>
<a href="<c:url value="http://localhost:8080/account/logout"/>"/>Log out
<br>
<a href="<c:url value="http://localhost:8080/deck/newDeck"/>"/>Create new deck
<br>
<a href="<c:url value="http://localhost:8080/deck/show/decks"/>"/>Show your decks
<br>
<a href="<c:url value="http://localhost:8080/deck/showScheduledDecks"/>"/>Scheduled decks
<br>
</body>
</html>
