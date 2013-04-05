<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>EasyFit - Users</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="EasyFit, track what you eat and stay fit !">
    <meta name="author" content="Hans Fortemaison">

    <!-- Le styles -->
    <link href="<c:url value="/assets/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/css/bootstrap-responsive.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/js/google-code-prettify/prettify.css"/>" rel="stylesheet">
</head>
<body onload="javascript:; document.getElementById('nav_users').setAttribute('class', 'active');">

<div class="container">
    <jsp:include page="../navigation.jsp"/>

    <a class="btn btn-primary pull-right" href="./edit">New user</a>

    <table class="table table-hover">
        <thead>
        <tr class="th">
            <th>User name</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Target weight</th>
            <th>Day points</th>
            <th>Extra points</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td class="td"><a href="./edit?key=${user.id}">${user.userName}</a></td>
                <td class="td">${user.firstName}</td>
                <td class="td">${user.lastName}</td>
                <td class="td">${user.targetWeight}</td>
                <td class="td">${user.dayPoints}</td>
                <td class="td">${user.extraPoints}</td>
                <td class="td"><a href="./delete?key=${user.id}"><i title="Delete" class="icon-remove"></i></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="../scripts.jsp"/>
</body>
</html>