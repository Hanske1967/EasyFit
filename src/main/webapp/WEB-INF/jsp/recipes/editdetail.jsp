<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="title"/></title>
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
    <link rel="stylesheet" href="<c:url value="/assets/css/custom.css"/>">

    <script src="<c:url value="/assets/js/jquery-2.0.0.min.js"/>"></script>
    <script>
        $(document).ready(function(){
            $('#nav_recipes').attr('class', 'active');
            $('#focusedInput').focus();
        });
    </script>

</head>

<body>

<div class="container">
    <jsp:include page="../navigation.jsp"/>
    <div class="myform">
        <form:form id="form" method="post" modelAttribute="recipeDetailForm" action="./adddetail3?">
            <fieldset>
                <legend>
                    ${recipeDetailForm.product.name}
                    <form:errors path="*" cssClass="errorBox"/>
                </legend>

                <form:hidden path="id"/>

                <form:label path="amount">
                    <fmt:message key="products.edit.amount"/><form:errors path="amount" cssClass="error"/>
                </form:label>
                <form:input id="focusedInput" path="amount" autocomplete="off"/>

                <form:label path="product.unitLabel">
                    <fmt:message key="products.edit.unit"/>
                </form:label>
                <form:input readonly="true" path="product.unitLabel"/>

                <p class="text-info">${recipeDetailForm.product.pointsLabel} <fmt:message key="products.edit.points"/>
                    / ${recipeDetailForm.product.amountLabel} ${recipeDetailForm.product.unitLabel}</p>

                <p class="text-info">${recipeDetailForm.product.description}</p>
            </fieldset>

            <button class="btn btn-primary" type="submit"><fmt:message key="action.submit"/></button>
        </form:form>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
<jsp:include page="../scripts.jsp"/>
</body>
</html>
