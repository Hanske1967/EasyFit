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
<header>
    <h1>EasyFit</h1>
</header>

<div id="gray_frame" class="center-640">
    <form:form id="login" name="form" method="post" action="/login" modelAttribute="userForm">
        <header id="login_header">
            Choose user:
        </header>

        <div id="login_form">
            <fieldset>
                <form:label path="id"/>
                <form:select path="id" items="${users}" autofocus="true"/>

                <form:label path="password"/>
                Password:
                <form:password path="password" size="20"/>
            </fieldset>

            <a href="javascript:;"
               onclick="document.getElementById('login').setAttribute('action', './changepwd');document.getElementById('login').submit();">Change
                password</a>

            <p>
                <button type="submit">Submit</button>
            </p>
        </div>
    </form:form>
</div>

</body>
</html>