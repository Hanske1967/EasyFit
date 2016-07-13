<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="title"/></title>
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

        #sidebar ul {
            margin-left: 0px;
        }

        #sidebar ul li {
            padding-bottom: 10px;
            list-style: none;
        }
    </style>
    <link href="<c:url value="/assets/css/bootstrap-responsive.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/js/google-code-prettify/prettify.css"/>" rel="stylesheet">

    <script src="<c:url value="/assets/js/jquery-2.0.0.min.js"/>"></script>
    <script>
        $(document).ready(function () {
            $('#nav_agenda').attr('class', 'active');
        });
    </script>

</head>

<body>

<div id="container" class="container">

    <!--  navigation  -->
    <jsp:include page="../navigation.jsp"/>

    <div class="row-fluid">
        <!-- left panel / infos -->
        <div id="sidebar" class="well well-small span3">
            <h4><fmt:message key="consumptions.list.thisweek"/></h4>
            <p><fmt:message key="consumptions.list.consumed"/>${consumptionForm.pointsLabel}<fmt:message key="consumptions.list.pts"/></p>
            <p><fmt:message key="consumptions.list.available"/>${consumptionForm.dayPointsLeftLabel}/ ${consumptionForm.dayPoints}<fmt:message key="consumptions.list.pts"/></p>
            <p><fmt:message key="consumptions.list.extra"/>${consumptionForm.extraPointsLeftLabel}/ ${consumptionForm.extraPoints}<fmt:message key="consumptions.list.pts"/></p>
            <p><fmt:message key="consumptions.list.excercises"/>${consumptionForm.excercisePointsLeftLabel}/ ${consumptionForm.excercisePointsLabel}<fmt:message key="consumptions.list.pts"/></p>
            <hr/>
            <ul>
                <c:forEach items="${consumptionWeek}" var="weekDay">
                    <li><a href="./list?date=${weekDay.currentDate}">${weekDay.weekDay}:${weekDay.pointsLabel}<fmt:message key="consumptions.list.pts"/></a>
                        <c:choose>
                            <c:when test="${weekDay.deltaPoints > 5}">
                                <span class="text-error"><strong>(${weekDay.deltaPointsLabel})</strong></span>
                            </c:when>
                            <c:when test="${weekDay.deltaPoints >= -5 && weekDay.deltaPoints <= 5}">
                                <span class="text-success"><strong>(${weekDay.deltaPointsLabel})</strong></span>
                            </c:when>
                            <c:otherwise>
                                <span class="text-warning"><strong>(${weekDay.deltaPointsLabel})</strong></span>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <!--  main panel  -->
        <div id="main" class="span9">

            <!--  navigation  -->
            <ul id="date_navigation" class="pager">
                <li>
                    <a href="./list?date=${consumptionForm.previousDate}">&larr;</a>
                </li>
                <li><strong>${consumptionForm.title}</strong></li>
                <li>
                    <a href="./list?date=${consumptionForm.nextDate}">&rarr;</a>
                </li>
            </ul>

            <!--  Time of the day: morning, evening...  -->
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
                        - ${consumptionForm.consumptionDetailsPointsForIndex}<fmt:message key="consumptions.list.pts"/>
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

            <!--  Excecise part (ConsumptionDetailType 7) -->
            <form id="consumptionDetail7" method="post" modelAttribute="consumptionDetailForm">
                <jsp:setProperty name="consumptionForm" property="typeIndex" value="7"/>

                <h5>
                    <c:choose>
                        <c:when test="${empty consumptionForm.id}">
                            <a class="btn btn-link pull-right" href="javascript:;"
                               onclick="document.getElementById('consumptionDetail7').setAttribute('action', './adddexcercise?date=${consumptionForm.currentDate}&amp;consumptionDetailType=7') ;document.getElementById('consumptionDetail7').submit();">
                                <i class="icon-plus"></i></a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-link pull-right" href="javascript:;"
                               onclick="document.getElementById('consumptionDetail7').setAttribute('action', './adddexcercise?key=${consumptionForm.id}&amp;consumptionDetailType=7') ;document.getElementById('consumptionDetail7').submit();">
                                <i class="icon-plus"></i></a>
                        </c:otherwise>
                    </c:choose>

                    <i class="icon-gift"></i>${consumptionForm.consumptionDetailTitleForIndex}
                    - ${consumptionForm.consumptionDetailsPointsForIndex} pts
                </h5>

                <div id="consumptionPanelDetail${type}" class="row-fluid">

                    <table class="table table-hover">
                        <tbody>
                        <c:forEach items="${consumptionForm.consumptionDetailsForIndex}" var="detail">
                            <tr>
                                <td class="span1">
                                    <a href="./removedetail?key=${consumptionForm.id}&amp;detailKey=${detail.id}">
                                        <i class="icon-remove"></i>
                                    </a>
                                </td>
                                <td class="span10"><a
                                        href="./editdetail?key=${consumptionForm.id}&amp;detailKey=${detail.id}">${detail.label}</a>
                                </td>
                                <td class="span1"><span
                                        class="badge badge-info">${detail.pointsLabel}</span></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>

            </form>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
<jsp:include page="../scripts.jsp"/>
</body>
</html>