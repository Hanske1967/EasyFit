<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Web Weights - Users</title>
    <meta charset="UTF-8">
    <link href="<c:url value="/theme.css" />" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="../navigation.jsp"/>
<h2>Users</h2>

<p>All users defined:</p>
<table class="table">
    <tr class="th">
        <th>User name</th>
        <th>Update date</th>
        <th colspan="2">Actions</th>
    </tr>

    <c:forEach items="${users}" var="user">
        <tr>
            <td class="td">${user.userName}</td>
            <td class="td">${user.updateDate}</td>
            <td class="td"><a href="./edit?username=${user.userName}">Edit</a></td>
            <td class="td"><a href="./delete?username=${user.userName}">Delete</a></td>
        </tr>
    </c:forEach>

</table>
<p><a href="./new">New user</a></p>
</body>
</html>