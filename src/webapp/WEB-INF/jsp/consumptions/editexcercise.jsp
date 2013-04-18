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

    <script>

        function chooseProduct(){

            var productSelect = document.getElementById('product');
            var excercice = ${exercises}[productSelect.selectedIndex];

            var unitLabel = document.getElementById('unitLabel');
            unitLabel.innerHTML = excercice.unitLabel;

            var productDescription = document.getElementById('productDescription');
            productDescription.innerHTML = excercice.pointsLabel + " pts / " + excercice.amountLabel + " " + excercice.unitLabel;
        }

    </script>
</head>
<body>

<div class="container">
    <jsp:include page="../navigation.jsp"/>
    <div class="form">
        <form:form id="form" method="post" modelAttribute="consumptionDetailForm" action="./adddetail3?">
            <fieldset>
                <legend>
                    Excercises
                    <form:errors path="*" cssClass="errorBox"/>
                </legend>

                <form:hidden path="id"/>

                <form:label path="product">
                    Product: <form:errors path="product" cssClass="error"/>
                </form:label>
                <form:select id="product" path="product" items="${excerciseLabels}" onchange="javascript:chooseProduct()"/>

                <form:label path="amount">
                    Amount: <form:errors path="amount" cssClass="error"/>
                </form:label>

                <div class="controls controls-row">
                    <form:input id="amount" cssClass="span3" path="amount" autocomplete="off" placeholder="amount"/>
                    <label id="unitLabel" class="span1"></label>
                </div>

                <label>Points:</label>
                <input id="points" type="text" placeholder="points">

                <p id="productDescription" class="text-info"></p>
            </fieldset>

            <button class="btn btn-primary" type="submit">Submit</button>
        </form:form>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
<jsp:include page="../scripts.jsp"/>
</body>
</html>
