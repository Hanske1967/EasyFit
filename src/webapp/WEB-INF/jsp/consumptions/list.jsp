<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>EasyFit - Agenda</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="EasyFit, track what you eat and stay fit !">
    <meta name="author" content="Hans Fortemaison">

    <!-- Le styles -->
    <link href="<c:url value="/assets/css/bootstrap.css"/>" rel="stylesheet">
    <style type="text/css">
        .well {
            padding-left: 30px;
        }
    </style>
    <link href="<c:url value="/assets/css/bootstrap-responsive.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/js/google-code-prettify/prettify.css"/>" rel="stylesheet">

</head>

<body onload="javascript:; document.getElementById('nav_agenda').setAttribute('class', 'active');">

<div id="container" class="container">

    <!--  navigation  -->
    <jsp:include page="../navigation.jsp"/>

    <div class="row-fluid">
        <!-- left panel / infos -->
        <div id="sidebar" class="well well-small span3">
            <h4>This week:</h4>

            <p>Consumed: ${consumptionForm.pointsLabel} pts</p>

            <p>Available: ${consumptionForm.dayPointsLeftLabel}/${consumptionForm.dayPoints}</p>

            <p>Extra: ${consumptionForm.extraPointsLeftLabel}/${consumptionForm.extraPoints}</p>
        </div>

        <!--  main panel  -->
        <div id="main" class="span9">

            <!--  navigation  -->
            <ul id="date_navigation" class="pager">
                <li>
                    <a href="./list?date=${consumptionForm.previousDate}">&larr;</a>
                </li>
                <li><strong><a href="javascript:;" onclick="$('#date_navigation').popover('show')"
                               data-toggle="tooltip">${consumptionForm.title}</a></strong></li>
                <li>
                    <a href="./list?date=${consumptionForm.nextDate}">&rarr;</a>
                </li>
            </ul>

            <c:forEach var="type" begin="1" end="6">
                <form id="consumptionDetail${type}" method="post" modelAttribute="consumptionDetailForm">
                    <jsp:setProperty name="consumptionForm" property="typeIndex" value="${type}"/>

                    <h5>
                        <c:choose>
                            <c:when test="${empty consumptionForm.id}">
                                <a class="btn btn-link pull-right" href="javascript:;"
                                   onclick="document.getElementById('consumptionDetail${type}').setAttribute('action', './adddetail1?date=${consumptionForm.currentDate}&amp;consumptionDetailType=${type}') ;document.getElementById('consumptionDetail${type}').submit();">
                                    <i class="icon-plus"></i></a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-link pull-right" href="javascript:;"
                                   onclick="document.getElementById('consumptionDetail${type}').setAttribute('action', './adddetail1?key=${consumptionForm.id}&amp;consumptionDetailType=${type}') ;document.getElementById('consumptionDetail${type}').submit();">
                                    <i class="icon-plus"></i></a>
                            </c:otherwise>
                        </c:choose>

                            ${consumptionForm.consumptionDetailTitleForIndex}
                        - ${consumptionForm.consumptionDetailsPointsForIndex} pts
                    </h5>


                    <div id="consumptionPanelDetail${type}" class="row-fluid">

                        <table class="table table-hover">
                            <tbody>
                            <c:forEach items="${consumptionForm.consumptionDetailsForIndex}" var="detail">
                                <tr>
                                    <td id="td_action" class="span1">
                                        <a href="./removedetail?key=${consumptionForm.id}&amp;detailKey=${detail.id}">
                                            <i class="icon-remove"></i>
                                        </a>
                                    </td>
                                    <td id="td_name" class="span10"><a
                                            href="./editdetail?key=${consumptionForm.id}&amp;detailKey=${detail.id}">${detail.label}</a>
                                    </td>
                                    <td id="td_points" class="span1"><span
                                            class="badge badge-info">${detail.pointsLabel}</span></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>

                </form>
            </c:forEach>

        </div>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
<jsp:include page="../scripts.jsp"/>
</body>
</html>