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

    <script src="<c:url value="/assets/js/jquery-2.0.0.min.js"/>"></script>
    <script>
        $(document).ready(function(){
            $('#nav_users').attr('class', 'active');
            $('#focusedInput').focus();
        });
    </script>

</head>

<body>

<div class="container">
    <jsp:include page="../navigation.jsp"/>
    <div class="form">
        <form:form id="form" name="form" method="post" modelAttribute="userForm">
            <fieldset>
                <legend>
                    <c:choose>
                        <c:when test="${empty userForm.userName}"><fmt:message key="users.edit.newUser"/></c:when>
                        <c:otherwise>${userForm.userName}</c:otherwise>
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

                <form:label path="userName">
                    <fmt:message key="users.edit.userName"/><form:errors path="userName" cssClass="error"/>
                </form:label>
                <form:input id="focusedInput" path="userName" maxlength="20"/>

                <form:label path="firstName">
                    <fmt:message key="users.edit.firstName"/><form:errors path="firstName" cssClass="error"/>
                </form:label>
                <form:input path="firstName" size="30" maxlength="30"/>

                <form:label path="lastName">
                    <fmt:message key="users.edit.lastName"/><form:errors path="lastName" cssClass="error"/>
                </form:label>
                <form:input path="lastName" size="50" maxlength="100"/>

                <form:label path="targetWeight">
                    <fmt:message key="users.edit.targetWeight"/><form:errors path="targetWeight" cssClass="error"/>
                </form:label>
                <form:input path="targetWeight"/>

                <form:label path="dayPoints">
                    <fmt:message key="users.edit.dayPoints"/><form:errors path="dayPoints" cssClass="error"/>
                </form:label>
                <form:input path="dayPoints"/>

                <form:label path="extraPoints">
                    <fmt:message key="users.edit.extraPoints"/><form:errors path="extraPoints" cssClass="error"/>
                </form:label>
                <form:input path="extraPoints"/>

                <form:label path="language">
                    <fmt:message key="users.edit.language"/><form:errors path="language" cssClass="error"/>
                </form:label>
                <form:select path="language">
                    <form:option value="nl"/>
                    <form:option value="fr"/>
                    <form:option value="en"/>
                </form:select>
            </fieldset>

            <button class="btn btn-primary" type="submit">Submit</button>
        </form:form>
    </div>
    <jsp:include page="../footer.jsp"/>
    <jsp:include page="../scripts.jsp"/>
</body>
</html>
