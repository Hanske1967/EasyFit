<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EasyFit - Categories</title>
    <meta charset="UTF-8">
    <link href="<c:url value="/theme.css" />" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="../navigation.jsp"/>
<h2>Categories</h2>

<p>All Categories defined:</p>
<table class="table">
    <tr class="th">
        <th>Name</th>
        <th>Actions</th>
    </tr>

    <c:forEach items="${categories}" var="category">
        <tr>
            <td class="td"><a href="./edit?key=${category.id}">${category.name}</a></td>
            <td class="td"><a href="./delete?key=${category.id}">Delete</a></td>
        </tr>
    </c:forEach>

</table>
<p><a href="./edit">New category</a></p>
</body>
</html>