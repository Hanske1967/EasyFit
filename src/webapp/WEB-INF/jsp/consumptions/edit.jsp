<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
    <title>WegWeights - Recipe</title>
    <meta charset="UTF-8">
    <link href="<c:url value="/theme.css" />" rel="stylesheet" type="text/css"/>
</head>
<body onload="document.getElementById('name').focus();">
<jsp:include page="../navigation.jsp"/>
<div name="form" class="myform">
    <form:form id="form" action="./update" method="post" modelAttribute="recipeForm">
        <div class="header">
            <c:choose>
                <c:when test="${empty recipeForm.id}"><h2>New recipe:</h2></c:when>
                <c:otherwise><h2>Update recipe:</h2></c:otherwise>
            </c:choose>

            <form:errors path="*" cssClass="errorBox"/>
        </div>
        <fieldset>
            <form:hidden path="id"/>

            <form:label path="name">
                Name <form:errors path="name" cssClass="error"/>
            </form:label>
            <form:input path="name"/>

            <form:label path="description">
                Description <form:errors path="description" cssClass="error"/>
            </form:label>
            <form:input path="description"/>

            <form:label path="point">
                Points: <form:errors path="point" cssClass="error"/>
            </form:label>
            <form:input readonly="true" path="point"/>

            <form:label path="favorite">
                Favorite <form:errors path="favorite" cssClass="error"/>
            </form:label>
            <form:checkbox path="favorite"/>

        </fieldset>

        <p>Products:</p>
        <table>
            <tr>
                <th>Amount</th>
                <th>Unit</th>
                <th>Name</th>
                <th>Points</th>
                <th colspan="2">Actions</th>
            </tr>

            <c:forEach items="${recipeForm.recipeDetailForms}" var="detail">
                <tr>
                    <td>${detail.amountLabel}</td>
                    <td>${detail.product.unit}</td>
                    <td>${detail.product.name}</td>
                    <td>${detail.points}</td>
                    <td class="td"><a href="editdetail?key=${recipeForm.id}&amp;detailKey=${detail.id}">Edit</a></td>
                    <td class="td"><a href="removedetail?key=${recipeForm.id}&amp;detailKey=${detail.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>

        </table>

        <c:choose>
            <c:when test="${empty recipeForm.id}">
                <a href="javascript:;"
                   onclick="document.getElementById('form').setAttribute('action', './adddetail1') ;document.getElementById('form').submit();">Add
                    product...</a>
            </c:when>
            <c:otherwise>
                <a href="javascript:;"
                   onclick="document.getElementById('form').setAttribute('action', './adddetail1?key=' + ${recipeForm.id}) ;document.getElementById('form').submit();">Add
                    product...</a>
            </c:otherwise>
        </c:choose>

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
