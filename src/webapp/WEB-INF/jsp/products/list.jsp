<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Web Weights - Products</title>
    <meta charset="UTF-8">
    <link href="<c:url value="/theme.css" />" rel="stylesheet" type="text/css"/>
</head>
</head>
<body>
<jsp:include page="../navigation.jsp"/>
<h2>Products</h2>

<div>
    <form action="/products/list" method="get">
        <p>Name: <input type="text" name="queryName" value="${queryName}"/><input type="submit" value="Search"/></p>
    </form>
</div>
<p>All recipeDetails defined:</p>
<table>
    <tr>
        <th>ID</th>
        <th>Favorite</th>
        <th>Name</th>
        <th>Amount</th>
        <th>Unit</th>
        <th>Points</th>
        <th>Description</th>
        <th colspan="2">Actions</th>
    </tr>

    <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.id}</td>
            <td><c:if test="${product.favorite}">*</c:if> &nbsp;</td>
            <td>${product.name}</td>
            <td>${product.amountLabel}</td>
            <td>${product.unit}</td>
            <td>${product.pointsLabel}</td>
            <td>${product.description}</td>
            <td class="td"><a href="./favorite?key=${product.id}">Favorite</a></td>
            <td class="td"><a href="./update?key=${product.id}">Edit</a></td>
            <td class="td"><a href="./delete?key=${product.id}">Delete</a></td>
        </tr>
    </c:forEach>

</table>

<p><a href="./new">New product</a></p>
</body>
</html>