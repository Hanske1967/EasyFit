<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>EasyFit - New product</title>
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
<body onload="
    javascript:;
    document.getElementById('nav_products').setAttribute('class', 'active');
    document.getElementById('focusedInput').focus()">

<div class="container">
    <jsp:include page="../navigation.jsp"/>
    <div class="form">
        <form:form id="form" method="post" modelAttribute="productForm">
            <fieldset>
                <legend>
                    <c:choose>
                        <c:when test="${empty productForm.id}">New product:</c:when>
                        <c:otherwise>Update product:</c:otherwise>
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

                <form:hidden path="id"/>

                <form:label path="name">
                    Name <form:errors path="name" cssClass="error"/>
                </form:label>
                <form:input id="focusedInput" path="name" class="input-xlarge" maxlength="255"/>

                <form:label path="amount">
                    Amount <form:errors path="amount" cssClass="error"/>
                </form:label>
                <form:input path="amount"/>

                <form:label path="unitId">
                    Unit <form:errors path="unitId" cssClass="error"/>
                </form:label>
                <form:select path="unitId" items="${allUnits}"/>

                <form:label path="points">
                    Points <form:errors path="points" cssClass="error"/>
                </form:label>
                <form:input path="points"/>

                <form:label path="maxPoints">
                    Max Points <form:errors path="maxPoints" cssClass="error"/>
                </form:label>
                <form:input path="maxPoints"/>

                <form:label path="categoryId">
                    Category <form:errors path="categoryId" cssClass="error"/>
                </form:label>
                <form:select path="categoryId" class="input-xlarge" items="${allCategories}"/>

                <form:label path="description">
                    Description <form:errors path="description" cssClass="error"/>
                </form:label>
                <form:input path="description" class="input-xxlarge" maxlength="255"/>

                <form:label path="shared">
                    Shared <form:errors path="shared" cssClass="error"/>
                </form:label>
                <form:checkbox path="shared"/>

            </fieldset>

            <button class="btn btn-primary" type="submit">Submit</button>
        </form:form>

    </div>
</div>
<jsp:include page="../scripts.jsp"/>
</body>
</html>
