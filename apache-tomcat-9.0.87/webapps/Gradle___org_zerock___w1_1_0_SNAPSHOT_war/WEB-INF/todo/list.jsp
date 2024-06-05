<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2024-04-03
  Time: 오후 3:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>ListPage</h1>
    <c:forEach var="dto" items="${list}">
        <li>${dto}</li>
    </c:forEach>
</body>
</html>
