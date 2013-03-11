<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
    <title>EasyFit - User</title>
    <meta charset="UTF-8">
    <link href="<c:url value="/theme.css" />" rel="stylesheet" type="text/css"/>
</head>
<body>

<jsp:include page="../navigation.jsp"/>
<div id="stylized" class="myform">
    <form:form id="form" name="form" method="post" modelAttribute="userForm">
        <div class="header">
            <c:choose>
                <c:when test="${empty userForm.userName}"><h2>New user:</h2></c:when>
                <c:otherwise><h2>Update user:</h2></c:otherwise>
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

            <form:label path="userName">
                User name:<form:errors path="userName" cssClass="error"/>
            </form:label>
            <form:input path="userName" maxlength="20" autofocus="true"/>

            <form:label path="firstName">
                First name:<form:errors path="firstName" cssClass="error"/>
            </form:label>
            <form:input path="firstName" maxlength="30"/>

            <form:label path="lastName">
                Last name:<form:errors path="lastName" cssClass="error"/>
            </form:label>
            <form:input path="lastName" maxlength="100"/>

            <form:label path="targetWeight">
                Target weight:<form:errors path="targetWeight" cssClass="error"/>
            </form:label>
            <form:input path="targetWeight"/>

            <form:label path="dayPoints">
                Day points:<form:errors path="dayPoints" cssClass="error"/>
            </form:label>
            <form:input path="dayPoints"/>

            <form:label path="extraPoints">
                Extra points:<form:errors path="extraPoints" cssClass="error"/>
            </form:label>
            <form:input path="extraPoints"/>

        </fieldset>

        <p>
            <button type="submit">Submit</button>
        </p>
    </form:form>

</div>
</body>
</html>
