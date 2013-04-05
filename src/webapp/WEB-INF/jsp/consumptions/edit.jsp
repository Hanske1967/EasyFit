<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>EasyFit - Agenda</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="EasyFit, track what you eat and stay fit !">
    <meta name="author" content="Hans Fortemaison">

    <!-- Le styles -->
    <link href="<c:url value="/assets/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/css/bootstrap-responsive.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/js/google-code-prettify/prettify.css"/>" rel="stylesheet">
</head>

<body onload="javascript:; document.getElementById('name').setAttribute('class', 'active'); document.getElementById('name').focus()">

<jsp:include page="../navigation.jsp"/>

<div name="form" class="myform">

    <form:form id="form" action="./update" method="post" modelAttribute="recipeForm">
        <legend>
            <c:choose>
                <c:when test="${empty recipeForm.id}"><h2>New recipe:</h2></c:when>
                <c:otherwise><h2>Update recipe:</h2></c:otherwise>
            </c:choose>

            <form:errors path="*" cssClass="errorBox"/>
        </legend>

        <fieldset>
            <form:hidden path="id"/>

            <form:label path="name">
                Name <form:errors path="name" cssClass="error"/>
            </form:label>
            <form:input path="name" autofocus="true"/>

            <form:label path="description">
                Description <form:errors path="description" cssClass="error"/>
            </form:label>
            <form:input path="description"/>

            <form:label path="points">
                Points: <form:errors path="points" cssClass="error"/>
            </form:label>
            <form:input readonly="true" path="points"/>

            <form:label path="shared">
                Favorite <form:errors path="shared" cssClass="error"/>
            </form:label>
            <form:checkbox path="shared"/>

        </fieldset>


        <table class="table">
            <caption>Products:</caption>

            <thead>
            <tr>
                <th>Amount</th>
                <th>Unit</th>
                <th>Name</th>
                <th>Points</th>
                <th colspan="2">Actions</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${recipeForm.recipeDetailForms}" var="detail">
                <tr>
                    <td>${detail.amountLabel}</td>
                    <td>${detail.product.unitLabel}</td>
                    <td>${detail.product.name}</td>
                    <td><span class="badge badge-info">${detail.points}</span></td>

                    <td class="td"><a href="editdetail?key=${recipeForm.id}&amp;detailKey=${detail.id}"><i
                            title="Delete" class="icon-edit"></i></a></td>
                    <td class="td"><a href="removedetail?key=${recipeForm.id}&amp;detailKey=${detail.id}"><i
                            title="Delete" class="icon-remove"></i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <c:choose>
            <c:when test="${empty recipeForm.id}">
                <a class="btn btn-primary" href="javascript:;"
                   onclick="document.getElementById('form').setAttribute('action', './adddetail1') ;document.getElementById('form').submit();">Add
                    product...</a>
            </c:when>
            <c:otherwise>
                <a class="btn btn-primary" href="javascript:;"
                   onclick="document.getElementById('form').setAttribute('action', './adddetail1?key=' + ${recipeForm.id}) ;document.getElementById('form').submit();">Add
                    product...</a>
            </c:otherwise>
        </c:choose>

        <p>
            <button type="submit">Submit</button>
        </p>
    </form:form>

</div>
</body>
</html>
