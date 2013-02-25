<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EasyFit - Agenda</title>
    <meta charset="UTF-8">
    <link href="<c:url value="/theme.css" />" rel="stylesheet" type="text/css"/>
</head>
</head>
<body>
<jsp:include page="../navigation.jsp"/>

<h2>
    <a href="./list?date=${consumptionForm.previousDate}"><<</a>
    ${consumptionForm.title} - ${consumptionForm.pointsLabel} points
    <a href="./list?date=${consumptionForm.nextDate}">>></a>
</h2>

<c:forEach var="type" begin="1" end="6">
    <form id="consumptionDetail${type}" method="post" modelAttribute="consumptionDetailForm">
        <jsp:setProperty name="consumptionForm" property="typeIndex" value="${type}"/>
        <h3>${consumptionForm.consumptionDetailTitleForIndex} - ${consumptionForm.consumptionDetailsPointsForIndex}
            points</h3>

        <p>
            <c:choose>
                <c:when test="${empty consumptionForm.id}">
                    <a href="javascript:;"
                       onclick="document.getElementById('consumptionDetail${type}').setAttribute('action', './adddetail1?date=${consumptionForm.currentDate}&amp;consumptionDetailType=${type}') ;document.getElementById('consumptionDetail${type}').submit();">Add
                        product...</a>
                </c:when>
                <c:otherwise>
                    <a href="javascript:;"
                       onclick="document.getElementById('consumptionDetail${type}').setAttribute('action', './adddetail1?key=${consumptionForm.id}&amp;consumptionDetailType=${type}') ;document.getElementById('consumptionDetail${type}').submit();">Add
                        product...</a>
                </c:otherwise>
            </c:choose>
        </p>
        <table>
            <tr>
                <th>ID</th>
                <th>Product</th>
                <th>Points</th>
                <th colspan="2">Actions</th>
            </tr>

            <c:forEach items="${consumptionForm.consumptionDetailsForIndex}" var="detail">
                <tr>
                    <td>${detail.id}</td>
                    <td>${detail.label}</td>
                    <td>${detail.pointsLabel}</td>
                    <td class="td"><a href="./editdetail?key=${consumptionForm.id}&amp;detailKey=${detail.id}">Edit</a>
                    </td>
                    <td class="td"><a
                            href="./removedetail?key=${consumptionForm.id}&amp;detailKey=${detail.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
    </form>
</c:forEach>
</body>
</html>