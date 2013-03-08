<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
    <title>EasyFit - Recipe</title>
    <meta charset="UTF-8">
    <link href="<c:url value="/theme.css" />" rel="stylesheet" type="text/css"/>
</head>
<body onload="document.getElementById('amount').focus();">
<jsp:include page="../navigation.jsp"/>
<div name="formdiv" class="myform">
    <form:form id="form" method="post" modelAttribute="recipeForm">
        <div class="header">
            <c:choose>
                <c:when test="${empty recipeForm.id}"><h2>New recipe:</h2></c:when>
                <c:otherwise><h2>Update recipe:</h2></c:otherwise>
            </c:choose>

            <form:errors path="*" cssClass="errorBox"/>
        </div>
        <fieldset>

            <form:hidden path="id"/>

            <form:label path="amount">
                Amount <form:errors path="amount" cssClass="error"/>
            </form:label>
            <form:input path="amount"/>

            <form:label path="unitId">
                Unit <form:errors path="unitId" cssClass="error"/>
            </form:label>
            <form:select path="unitId" items="${allUnits}"/>

            <form:label path="name">
                Name <form:errors path="name" cssClass="error"/>
            </form:label>
            <form:input path="name"/>

            <form:label path="description">
                Description <form:errors path="description" cssClass="error"/>
            </form:label>
            <form:input path="description"/>

            <form:label path="pointsLabel">
                Points: <form:errors path="pointsLabel" cssClass="error"/>
            </form:label>
            <form:input readonly="true" path="pointsLabel"/>

            <form:label path="favorite">
                Favorite <form:errors path="favorite" cssClass="error"/>
            </form:label>
            <form:checkbox path="favorite"/>

        </fieldset>

        <p>Products:</p>
        <table id="product_table">
            <tr>
                <th id="th_amount">Amount</th>
                <th id="th_unit">Unit</th>
                <th id="th_name">Name</th>
                <th id="th_points">Points</th>
                <th id="th_actions" colspan="2">Actions</th>
            </tr>

            <c:forEach items="${recipeForm.recipeDetailForms}" var="detail">
                <tr>
                    <td id="td_amount">${detail.amount}</td>
                    <td id="td_unit">${detail.product.unitLabel}</td>
                    <td id="td_name">${detail.product.name}</td>
                    <td id="td_points">${detail.pointsLabel}</td>
                    <td id="td_edit" class="td"><a href="editdetail?key=${recipeForm.id}&amp;detailKey=${detail.id}">Edit</a>
                    </td>
                    <td id="td_remove" class="td"><a
                            href="removedetail?key=${recipeForm.id}&amp;detailKey=${detail.id}">Delete</a>
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
