<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            $('#oldPassword').focus();
        });
    </script>

</head>

<body>

<div id="container" class="container">

    <!--  navigation  -->
    <jsp:include page="../navigation.jsp"/>
    <div class="form">
        <form:form id="form" name="form" action="./changepwd" method="post" modelAttribute="userForm">
            <fieldset>
                <legend><fmt:message key="users.changepwd.changePwdForUser"/> ${userForm.userName}</legend>

                <form:hidden path="id"/>

                <form:label path="oldPassword"><fmt:message key="users.changepwd.oldPassword"/></form:label>
                <form:input path="oldPassword" autocomplete="off"/>

                <form:label path="password"><fmt:message key="users.changepwd.newPassword"/></form:label>
                <form:input path="password" autocomplete="off"/>
            </fieldset>

            <button class="btn btn-primary" type="submit"><fmt:message key="action.submit"/></button>
        </form:form>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
<jsp:include page="../scripts.jsp"/>
</body>
</html>