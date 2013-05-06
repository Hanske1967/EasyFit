<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
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

    <script src="<c:url value="/assets/js/jquery-2.0.0.min.js"/>"></script>
    <script src="<c:url value="/assets/js/jquery-2.0.0.min.js"/>"></script>
    <script>
        $.ready(function(){
            $('#userid').focus();
        });
    </script>

</head>

<body>

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
                        <form:select id="userid" path="id" items="${users}" autofocus="true" onselect="userSelected()"/>
                    </div>
                </div>

                <div class="control-group">
                    <form:label path="password">Password:</form:label>

                    <div class="controls">
                        <form:password path="password" size="20" autocomplete="off"/>
                    </div>
                </div>

            </fieldset>

            <div class="control-group">
                <p><a id="passwordaction" href="#">Change password</a></p>
            </div>

            <div class="control-group">
                <button class="btn btn-primary" type="submit" class="btn">Submit</button>
            </div>

        </form:form>
    </div>

    <jsp:include page="footer.jsp"/>
</div>

<script type="text/javascript">
    document.getElementById('userid').onchange = function(x){
        var combo =  document.getElementById('userid');
        var userid = combo.options[combo.selectedIndex].value;
        var changePwdUrl = "${pageContext.servletContext.contextPath}/changepwd?userid=" + userid;
        document.getElementById("passwordaction").setAttribute("href", changePwdUrl);
    };
</script>

</body>
</html>