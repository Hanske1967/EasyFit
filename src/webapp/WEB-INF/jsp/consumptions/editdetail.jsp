<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <style>
        .btn {
            margin-top: 20px;
        }
    </style>
    <link href="<c:url value="/assets/css/bootstrap-responsive.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/js/google-code-prettify/prettify.css"/>" rel="stylesheet">
</head>
<body onload="javascript:; document.getElementById('nav_agenda').setAttribute('class', 'active');
 document.getElementById('focusedInput').focus()">

<div class="container">
    <jsp:include page="../navigation.jsp"/>
    <div class="form">
        <form:form id="form" method="post" modelAttribute="consumptionDetailForm" action="./adddetail3?">
            <fieldset>
                <legend>
                    ${consumptionDetailForm.product.name}
                    <form:errors path="*" cssClass="errorBox"/>
                </legend>

                <form:hidden path="id"/>

                <form:label path="amount">
                    Amount: <form:errors path="amount" cssClass="error"/>
                </form:label>
                <form:input id="focusedInput" path="amount" autocomplete="false"/>

                <form:label path="productUnit">
                    Unit: <form:errors path="productUnit" cssClass="error"/>
                </form:label>
                <form:input readonly="true" path="productUnit"/>

                <p class="text-info">${consumptionDetailForm.product.pointsLabel} pts
                    / ${consumptionDetailForm.product.amountLabel} ${consumptionDetailForm.product.unitLabel}</p>

                <p class="text-info">${consumptionDetailForm.product.description}</p>
            </fieldset>

            <button class="btn btn-primary" type="submit">Submit</button>
        </form:form>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
<jsp:include page="../scripts.jsp"/>
</body>
</html>
