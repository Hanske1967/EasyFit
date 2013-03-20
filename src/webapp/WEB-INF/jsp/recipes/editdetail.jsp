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
    <form:form id="form" method="post" modelAttribute="recipeDetailForm" action="./adddetail3?">
        <div class="header">
            <c:choose>
                <c:when test="${empty recipeDetailForm.id}"><h2>New recipe product:</h2></c:when>
                <c:otherwise><h2>Update recipe product:</h2></c:otherwise>
            </c:choose>

            <form:errors path="*" cssClass="errorBox"/>
        </div>
        <fieldset>
            <form:hidden path="id"/>

            <form:label path="amount">
                Amount: <form:errors path="amount" cssClass="error"/>
            </form:label>
            <form:input path="amount" autofocus="true"/>

            <form:label path="product.unitLabel">
                Unit:
            </form:label>
            <form:input readonly="true" path="product.unitLabel"/>

            <form:label path="product.name">
                Product:
            </form:label>
            <form:input readonly="true" path="product.name"/>

            <form:label path="points">
                Points: <form:errors path="points" cssClass="error"/>
            </form:label>
            <form:input readonly="true" path="points"/>
        </fieldset>

        <p>
            <button type="submit">Submit</button>
        </p>
    </form:form>
</div>
</body>
</html>
