<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EasyFit - Recipes</title>
    <meta charset="UTF-8">
    <link href="<c:url value="/theme.css" />" rel="stylesheet" type="text/css"/>
</head>
</head>
<body>
<jsp:include page="../navigation.jsp"/>
<h2>Recipes</h2>

<p>All recipes defined:</p>
<table>
    <tr>
        <th>Shared</th>
        <th>Name</th>
        <th>Points</th>
        <th>Category</th>
        <th>Description</th>
        <th colspan="2">Actions</th>
    </tr>

    <c:forEach items="${recipes}" var="recipe">
        <tr>
            <td><c:if test="${recipe.shared}">*</c:if> &nbsp;</td>
            <td><a href="./edit?key=${recipe.id}">${recipe.name}</a></td>
            <td>${recipe.pointsLabel}</td>
            <td>${recipe.categoryLabel}</td>
            <td>${recipe.description}</td>
            <td class="td"><a href="./shared?key=${recipe.id}">Favorite</a></td>
            <td class="td"><a href="./delete?key=${recipe.id}">Delete</a></td>
        </tr>
    </c:forEach>

</table>

<p><a href="./edit">New recipe</a></p>
</body>
</html>