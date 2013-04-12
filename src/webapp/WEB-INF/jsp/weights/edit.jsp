<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>EasyFit - New weight</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="EasyFit, track what you eat and stay fit !">
    <meta name="author" content="Hans Fortemaison">

    <!-- Le styles -->
    <link href="<c:url value="/assets/css/bootstrap.css"/>" rel="stylesheet">
    <style>
        .btn {
            margin-top: 20px;
        }
    </style>
    <link href="<c:url value="/assets/css/bootstrap-responsive.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/js/google-code-prettify/prettify.css"/>" rel="stylesheet">

    <!-- jquery ui style -->
    <link rel="stylesheet" href="/assets/js/jquery-ui-1.10.2/themes/base/jquery.ui.datepicker.css">
</head>
<body onload="
    javascript:;
    document.getElementById('nav_weights').setAttribute('class', 'active');
    document.getElementById('datepicker').focus()">

<div class="container">
    <jsp:include page="../navigation.jsp"/>
    <div class="form">
        <form:form id="form" name="form" method="post" modelAttribute="weightForm">
        <fieldset>
            <legend>
                <c:choose>
                    <c:when test="${empty weightForm.id}">New weight:</c:when>
                    <c:otherwise>${weightForm.date}</c:otherwise>
                </c:choose>
                <c:if test="${not empty message}">
                    <div id="message" class="success">${message}</div>
                </c:if>
                <s:bind path="*">
                    <c:if test="${status.error}">
                        <div id="message" class="error">Form has errors</div>
                    </c:if>
                </s:bind>
            </legend>
            <fieldset>
                <form:hidden path="id"/>
                <form:label path="date">
                    Date <form:errors path="date" cssClass="error"/>
                </form:label>
                <form:input id="datepicker" path="dateStr"/>

                <form:label path="weight">
                    Weight <form:errors path="weight" cssClass="error"/>
                </form:label>
                <form:input path="weight" autocomplete="false"/>
            </fieldset>
            <button class="btn btn-primary" type="submit">Submit</button>
            </form:form>
    </div>
    <jsp:include page="../scripts.jsp"/>

    <!--

    <script src="<c:url value="/assets/js/jquery-ui-1.10.2/ui/jquery.ui.core.js"/>"></script>
    <script src="<c:url value="/assets/js/jquery-ui-1.10.2/ui/jquery.ui.widget.js"/>"></script>
    <script src="<c:url value="/assets/js/jquery-ui-1.10.2/ui/jquery.ui.datepicker.js"/>"></script>
    <script>
        $(function () {
            $("#datepicker").datepicker();
        });
    </script>

-->
</body>
<jsp:include page="../footer.jsp"/>
<jsp:include page="../scripts.jsp"/>
</html>
