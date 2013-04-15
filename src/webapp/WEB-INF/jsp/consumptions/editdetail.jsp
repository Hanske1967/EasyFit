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

    <script lang="javascript">

        function updatePoints() {

            consummedAmount = document.getElementById('amount').value;

            productPoints = '${consumptionDetailForm.product.points}';
            productAmount = '${consumptionDetailForm.product.amount}';

            resultPoints = (consummedAmount * productPoints / productAmount).toFixed(1);
            document.getElementById("points").value = resultPoints;
        }

        function updateAmount() {
            points = document.getElementById('points').value;

            productPoints = '${consumptionDetailForm.product.points}';
            productAmount = '${consumptionDetailForm.product.amount}';

            resultAmount = (productAmount * points / productPoints).toFixed(1);
            document.getElementById("amount").value = resultAmount;
        }

        function onload() {
            document.getElementById('nav_agenda').setAttribute('class', 'active');
            updatePoints();
            document.getElementById('amount').focus();
        }

    </script>
</head>
<body onload="javascript:onload()">

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

                <div class="controls controls-row">
                    <form:input id="amount" cssClass="span3" path="amount" autocomplete="off" placeholder="amount"
                                onchange="javascript:updatePoints()"/>
                    <label class="span1">${consumptionDetailForm.productUnit}</label>
                </div>

                <label>Points:</label>
                <input id="points" type="text" placeholder="points" onchange="javascript:updateAmount()"/>

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
