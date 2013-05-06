<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>EasyFit - New category</title>
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

    <script src="<c:url value="/assets/js/jquery-2.0.0.min.js"/>"></script>
    <script>
        $(document).ready(function(){
            $('#nav_categories').attr('class', 'active');
            $('#focusedInput').focus();
        });
    </script>

</head>
<body>

<div class="container">
    <jsp:include page="../navigation.jsp"/>
    <div class="form">
        <form:form id="form" name="form" method="post" modelAttribute="productCategoryForm">
            <fieldset>
                <legend>
                    <c:choose>
                        <c:when test="${empty categoryForm.id}">New category:</c:when>
                        <c:otherwise>${categoryForm.name}</c:otherwise>
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
                <form:input id="focusedInput" path="name" maxlength="50" class="input-xlarge"/>

                <form:label path="shared">
                    Shared <form:errors path="shared" cssClass="error"/>
                </form:label>
                <form:checkbox path="shared"/>
            </fieldset>
            <button class="btn btn-primary" type="submit">Submit</button>
        </form:form>
    </div>
    <jsp:include page="../footer.jsp"/>
    <jsp:include page="../scripts.jsp"/>

</body>

</html>
