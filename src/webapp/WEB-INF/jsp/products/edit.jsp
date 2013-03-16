<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
    <title>EasyFit - New product</title>
    <meta charset="UTF-8">
    <link href="<c:url value="/theme.css" />" rel="stylesheet" type="text/css"/>
</head>
<body
">
<jsp:include page="../navigation.jsp"/>
<div id="stylized" class="myform">
    <form:form id="form" method="post" modelAttribute="productForm">
        <div class="header">
            <c:choose>
                <c:when test="${empty productForm.id}"><h2>New product:</h2></c:when>
                <c:otherwise><h2>Update product:</h2></c:otherwise>
            </c:choose>
            <c:if test="${not empty message}">
                <div id="message" class="success">${message}</div>
            </c:if>
            <s:bind path="*">
                <c:if test="${status.error}">
                    <div id="message" class="error">Form has errors</div>
                </c:if>
            </s:bind>
        </div>
        <fieldset>
            <form:hidden path="id"/>

            <form:label path="name">
                Name <form:errors path="name" cssClass="error"/>
            </form:label>
            <form:input path="name" size="50" maxlength="255" autofocus="true"/>

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
            <form:select path="categoryId" items="${allCategories}"/>

            <form:label path="description">
                Description <form:errors path="description" cssClass="error"/>
            </form:label>
            <form:input path="description" size="50" maxlength="255"/>

            <form:label path="shared">
                Favorite <form:errors path="shared" cssClass="error"/>
            </form:label>
            <form:checkbox path="shared"/>

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
