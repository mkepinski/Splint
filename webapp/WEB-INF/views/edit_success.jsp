<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Card successfully edited!</title>
</head>
<body>
<%@ include file="header.jsp"%>
<h2>Card successfully updated!</h2>
<a href="<c:url value="http://localhost:8080/deck/show/deck/${deck.deckName}"/>"/>Return to deck
</body>
</html>
