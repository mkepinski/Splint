<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log out</title>
</head>
<body>
Are you sure you want to log out?
<span><a href="<c:url value="http://localhost:8080/account/logout/confirmed"/>"/>Yes</span>
<span><a href="<c:url value="http://localhost:8080/"/>"/>No</span>
</body>
</html>
