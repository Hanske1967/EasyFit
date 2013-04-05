<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>EasyFit - Products</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="EasyFit, track what you eat and stay fit !">
    <meta name="author" content="Hans Fortemaison">

    <!-- Le styles -->
    <link href="<c:url value="/assets/css/bootstrap.css"/>" rel="stylesheet">

    <link href="<c:url value="/assets/css/bootstrap-responsive.css"/>" rel="stylesheet">

    <link href="<c:url value="/assets/js/google-code-prettify/prettify.css"/>" rel="stylesheet">

</head>
<body onload="javascript:; document.getElementById('nav_agenda').setAttribute('class', 'active'); document.getElementById('focusedInput').focus()">

<div class="container">
    <jsp:include page="../navigation.jsp"/>

    <form id="form" class="form-inline" method="post" action="./adddetail1?">
        <input id="focusedInput" type="text" name="queryName" value="${queryName}" placeholder="product name"
               autofocus="true"/>
        <select name="category" value="${category}">
            <option value="">---</option>
            <c:forEach items="${allCategories}" var="cat">
                <option value="${cat.key}">${cat.value}</option>
            </c:forEach>
        </select>
        <button type="submit" class="btn">Search</button>
        </fieldset>
    </form>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>Amount</th>
            <th>Unit</th>
            <th>Name</th>
            <th>Points</th>
            <th>Max Points</th>
            <span class="visible-desktop">
                <th>Description</th>
            </span>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.amount}</td>
                <td>${product.unitLabel}</td>
                <td>
                    <a href="javascript:;"
                       onclick="document.getElementById('form').setAttribute('action', './adddetail2?productKey=${product.id}') ;document.getElementById('form').submit();">${product.name}</a>
                </td>
                <td><span class="badge badge-info">${product.pointsLabel}</span></td>
                <td><span class="badge badge-success">${product.maxPoints}</span></td>
                <span class="visible-desktop">
                    <td>${product.description}</td>
                </span>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </form>
</div>
<jsp:include page="../scripts.jsp"/>
</body>
</html>