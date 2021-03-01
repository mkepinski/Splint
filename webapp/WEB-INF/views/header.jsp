<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="header">
    <a href="#default" class="logo">SPLINT</a>
    <div class="header-right">
        <a class="active" href="<c:url value="http://localhost:8080/account/new"/>">Create new account</a>
        <a class="active" href="<c:url value="http://localhost:8080/account/login"/>">Log in</a>
        <a class="active" href="<c:url value="http://localhost:8080/account/logout"/>">Log out</a>
        <a class="active" href="<c:url value="http://localhost:8080/deck/newDeck"/>">Create new deck</a>
        <a class="active" href="<c:url value="http://localhost:8080/deck/show/decks"/>">Show all decks</a>
        <a class="active" href="<c:url value="http://localhost:8080/deck/showScheduledDecks"/>">Show scheduled decks</a>
    </div>
</div>
</body>
</html>
