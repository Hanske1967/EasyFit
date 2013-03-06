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
<body onload="document.getElementById('queryName').focus()">
<jsp:include page="../navigation.jsp"/>
<h2>Products</h2>

<form id="form" method="post" action="./adddetail1?">
    <div name="search">
        <p>Name: <input type="text" id="queryName" name="queryName" value="${queryName}"/><input type="submit"
                                                                                                 value="Search"/></p>
    </div>
    <p>All products & recipes defined:</p>
    <table>
        <tr>
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
                <td><c:if test="${product.favorite}">*</c:if> &nbsp;</td>
                <td>
                    <a href="javascript:;"
                       onclick="document.getElementById('form').setAttribute('action', './adddetail2?productKey=${product.id}') ;document.getElementById('form').submit();">${product.name}</a>
                </td>
                <td>${product.amount}</td>
                <td>${product.unitLabel}</td>
                <td>${product.pointsLabel}</td>
                <td>${product.description}</td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>