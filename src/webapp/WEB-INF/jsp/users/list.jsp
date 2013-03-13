<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EasyFit - Users</title>
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
        <th>First name</th>
        <th>Last name</th>
        <th>Target weight</th>
        <th>Day points</th>
        <th>Extra points</th>
        <th>Actions</th>
    </tr>

    <c:forEach items="${users}" var="user">
        <tr>
            <td class="td"><a href="./edit?key=${user.id}">${user.userName}</a></td>
            <td class="td">${user.firstName}</td>
            <td class="td">${user.lastName}</td>
            <td class="td">${user.targetWeight}</td>
            <td class="td">${user.dayPoints}</td>
            <td class="td">${user.extraPoints}</td>
            <td class="td"><a href="./delete?key=${user.id}">Delete</a></td>
        </tr>
    </c:forEach>

</table>
<p><a href="./edit">New user</a></p>
</body>
</html>