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

<h2 class="date_header">
    <p>${userForm.userName}</p>
    <a href="./list?date=${consumptionForm.previousDate}"><img src="../images/Actions-go-previous-view-icon.png"
                                                               alt="<<"></a>

    <p>${consumptionForm.title}</p>
    <a href="./list?date=${consumptionForm.nextDate}"><img src="../images/Actions-go-next-view-icon.png" alt=">>"></a>

    <p>Consumed: ${consumptionForm.pointsLabel} pts,
        Available: ${consumptionForm.dayPointsLeftLabel}/${consumptionForm.dayPoints},
        Extra: ${consumptionForm.extraPointsLeftLabel}/${consumptionForm.extraPoints}</p>
</h2>

<c:forEach var="type" begin="1" end="6">
    <form id="consumptionDetail${type}" method="post" modelAttribute="consumptionDetailForm">
        <jsp:setProperty name="consumptionForm" property="typeIndex" value="${type}"/>
        <h3>${consumptionForm.consumptionDetailTitleForIndex} - ${consumptionForm.consumptionDetailsPointsForIndex}
            points

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
        </h3>
        <table>
            <tr>
                <th id="th_name">Product</th>
                <th id="th_points">Points</th>
                <th id="th_actions">Actions</th>
            </tr>

            <c:forEach items="${consumptionForm.consumptionDetailsForIndex}" var="detail">
                <tr>
                    <td id="td_name"><a
                            href="./editdetail?key=${consumptionForm.id}&amp;detailKey=${detail.id}">${detail.label}</a>
                    </td>
                    <td id="td_points">${detail.pointsLabel}</td>
                    <td id="td_action" class="td"><a
                            href="./removedetail?key=${consumptionForm.id}&amp;detailKey=${detail.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
</c:forEach>
</body>
</html>