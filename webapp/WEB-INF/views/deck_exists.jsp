<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Deck already exists
    </title>
</head>
<body>
<%@ include file="header.jsp"%>
Deck with this name already exists!
<br>
<a href="<c:url value="http://localhost:8080/deck/newDeck"/>"/>Go back
</body>
</html>
