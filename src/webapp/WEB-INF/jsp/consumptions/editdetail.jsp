<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
    <title>EasyFit - Recipe</title>
    <link href="<c:url value="/theme.css" />" rel="stylesheet" type="text/css"/>
    <meta charset="UTF-8">
</head>
<body>
<jsp:include page="../navigation.jsp"/>
<div class="myform">
    <form:form id="form" method="post" modelAttribute="consumptionDetailForm" action="./adddetail3?">
        <div class="header">
            <c:choose>
                <c:when test="${empty consumptionDetailForm.id}"><h2>Add product:</h2></c:when>
                <c:otherwise><h2>Update product:</h2></c:otherwise>
            </c:choose>

            <form:errors path="*" cssClass="errorBox"/>
        </div>
        <fieldset>
            <form:hidden path="id"/>

            <form:label path="amount">
                Amount: <form:errors path="amount" cssClass="error"/>
            </form:label>
            <form:input path="amount" autofocus="true"/>

            <form:label path="productUnit">
                Unit: <form:errors path="productUnit" cssClass="error"/>
            </form:label>
            <form:input readonly="true" path="productUnit"/>

            <form:label path="productName">
                Product: <form:errors path="productName" cssClass="error"/>
            </form:label>
            <form:input readonly="true" path="productName"/>

            <form:label path="points">
                Points: <form:errors path="points" cssClass="error"/>
            </form:label>
            <form:input readonly="true" path="points"/>
        </fieldset>

        <p>
            <button type="submit">Submit</button>
        </p>
    </form:form>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#form").submit(function () {
                $.post($(this).attr("action"), $(this).serialize(), function (html) {
                    $("#myform").replaceWith(html);
                    $('html, body').animate({ scrollTop: $("#message").offset().top }, 500);
                });
                return false;
            });
        });
    </script>
</div>
</body>
</html>
