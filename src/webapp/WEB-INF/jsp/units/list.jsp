<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Web Weights - Units</title>
    <meta charset="UTF-8">
    <link href="<c:url value="/theme.css" />" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="../navigation.jsp"/>
<h2>Units</h2>

<p>All units defined:</p>
<table class="table">
    <tr class="th">
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th colspan="2">Actions</th>
    </tr>

    <c:forEach items="${units}" var="unit">
        <tr>
            <td class="td">${unit.id}</td>
            <td class="td">${unit.name}</td>
            <td class="td">${unit.description}</td>
            <td class="td"><a href="./update?key=${unit.id}">Edit</a></td>
            <td class="td"><a href="./delete?key=${unit.id}">Delete</a></td>
        </tr>
    </c:forEach>

</table>
<p><a href="./new">New unit</a></p>
</body>
</html>