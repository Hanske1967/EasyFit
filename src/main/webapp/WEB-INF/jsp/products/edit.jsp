<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
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
            $('#nav_products').attr('class', 'active');
            $('#focusedInput').focus();
        });
    </script>

</head>
<body>
<div class="container">
    <jsp:include page="../navigation.jsp"/>
    <div class="form">
        <form:form id="form" method="post" modelAttribute="productForm">
            <fieldset>
                <legend>
                    <c:choose>
                        <c:when test="${empty productForm.id}"><fmt:message key="products.edit.newProduct"/></c:when>
                        <c:otherwise>${productForm.name}</c:otherwise>
                    </c:choose>
                    <c:if test="${not empty message}">
                        <div id="message" class="success">${message}</div>
                    </c:if>
                    <s:bind path="*">
                        <c:if test="${status.error}">
                            <div id="message" class="error"><fmt:message key="error.formHasErrors"/></div>
                        </c:if>
                    </s:bind>
                </legend>

                <form:hidden path="id"/>

                <form:label path="name">
                    <fmt:message key="products.edit.name"/><form:errors path="name" cssClass="error"/>
                </form:label>
                <form:input id="focusedInput" path="name" class="input-xlarge" maxlength="255"/>

                <form:label path="amount">
                    <fmt:message key="products.edit.amount"/><form:errors path="amount" cssClass="error"/>
                </form:label>
                <form:input path="amount"/>

                <form:label path="unitId">
                    <fmt:message key="products.edit.unit"/><form:errors path="unitId" cssClass="error"/>
                </form:label>
                <form:select path="unitId" items="${allUnits}"/>

                <form:label path="points">
                    <fmt:message key="products.edit.points"/><form:errors path="points" cssClass="error"/>
                </form:label>
                <form:input path="points" autocomplete="off"/>

                <form:label path="maxPoints">
                    <fmt:message key="products.edit.maxPoints"/><form:errors path="maxPoints" cssClass="error"/>
                </form:label>
                <form:input path="maxPoints" autocomplete="off"/>

                <form:label path="categoryId">
                    <fmt:message key="products.edit.category"/><form:errors path="categoryId" cssClass="error"/>
                </form:label>
                <form:select path="categoryId" class="input-xlarge" items="${allCategories}"/>

                <form:label path="description">
                    <fmt:message key="products.edit.description"/><form:errors path="description" cssClass="error"/>
                </form:label>
                <form:input path="description" class="input-xxlarge" maxlength="255"/>

                <form:label path="shared">
                    <fmt:message key="products.edit.shared"/><form:errors path="shared" cssClass="error"/>
                </form:label>
                <form:checkbox path="shared"/>
            </fieldset>

            <button class="btn btn-primary" type="submit"><fmt:message key="action.submit"/></button>
        </form:form>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
<jsp:include page="../scripts.jsp"/>
</body>
</html>
