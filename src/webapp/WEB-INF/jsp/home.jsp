<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EasyFit - Home</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="EasyFit, track what you eat and stay fit !">
    <meta name="author" content="Hans Fortemaison">

    <!-- Le styles -->
    <link href="<c:url value="/assets/css/bootstrap.css"/>" rel="stylesheet">
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            max-width: 300px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
        }

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }

        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }

    </style>


    <link href="<c:url value="/assets/css/bootstrap-responsive.css"/>" rel="stylesheet">

    <link href="<c:url value="/assets/js/google-code-prettify/prettify.css"/>" rel="stylesheet">

</head>
<body onload="document.getElementById('userid').focus()">


<div id="container" class="container">
    <div class="masthead">
        <h2>EasyFit</h2>
    </div>

    <div class="form-signin">
        <form:form id="login" name="form" method="post" action="/login" modelAttribute="userForm">
            <h2 class="form-signin-heading">Please sign in</h2>

            <fieldset>
                <div class="control-group">
                    <form:label path="id">User:</form:label>

                    <div class="controls">
                        <form:select id="userid" path="id" items="${users}" autofocus="true"/>
                    </div>
                </div>

                <div class="control-group">
                    <form:label path="password">Password:</form:label>

                    <div class="controls">
                        <form:password path="password" size="20"/>
                    </div>
                </div>

            </fieldset>

            <div class="control-group">
                <p class="text-info"><a href="javascript:;"
                                        onclick="document.getElementById('login').setAttribute('action', './changepwd');document.getElementById('login').submit();">Change
                    password</a>
                </p>
            </div>

            <div class="control-group">
                <button class="btn btn-primary" type="submit" class="btn">Submit</button>
            </div>

        </form:form>
    </div>
</div>

</body>
</html>