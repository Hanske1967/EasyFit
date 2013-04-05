<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>EasyFit - Categories</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="EasyFit, track what you eat and stay fit !">
    <meta name="author" content="Hans Fortemaison">

    <!-- Le styles -->
    <link href="<c:url value="/assets/css/bootstrap.css"/>" rel="stylesheet">

    <link href="<c:url value="/assets/css/bootstrap-responsive.css"/>" rel="stylesheet">

    <link href="<c:url value="/assets/js/google-code-prettify/prettify.css"/>" rel="stylesheet">

</head>
<body onload="javascript:; document.getElementById('nav_categories').setAttribute('class', 'active');">

<div class="container">
    <jsp:include page="../navigation.jsp"/>

    <a class="btn btn-primary pull-right" href="./edit">New category</a>


    <table class="table table-hover">
        <thead>
        <tr class="th">
            <th>Name</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${categories}" var="category">
            <tr>
                <td><a href="./edit?key=${category.id}">${category.name}</a></td>
                <td class="td"><a href="./delete?key=${category.id}"><i title="Delete" class="icon-remove"></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <jsp:include page="../scripts.jsp"/>
</div>
</body>
</html>