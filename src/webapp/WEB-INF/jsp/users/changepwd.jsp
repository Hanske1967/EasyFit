<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EasyFit - Home</title>
    <meta charset="UTF-8">
    <link href="<c:url value="/theme.css" />" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="../navigation.jsp"/>

<div id="stylized" class="myform">
    <form:form id="form" name="form" action="./changepwd" method="post" modelAttribute="userForm">
        <div class="header">
            Enter password:
        </div>

        <fieldset>
            <form:hidden path="id"/>

            <form:label path="password"/>
            <form:input path="password" size="20" autofocus="true"/>
        </fieldset>

        <p>
            <button type="submit">Submit</button>
        </p>
    </form:form>
</div>

</body>
</html>