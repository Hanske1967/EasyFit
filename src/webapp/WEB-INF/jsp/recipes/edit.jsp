<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link href="<c:url value="/assets/css/bootstrap-responsive.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/js/google-code-prettify/prettify.css"/>" rel="stylesheet">

    <script src="<c:url value="/assets/js/jquery-2.0.0.min.js"/>"></script>
    <script>
        $(document).ready(function(){
            $('#nav_recipes').attr('class', 'active');
            $('#focusedInput').focus();
        });
    </script>

</head>
<body>
<div class="container">
    <jsp:include page="../navigation.jsp"/>
    <div class="form">
        <form:form id="form" method="post" modelAttribute="recipeForm">
            <fieldset>
                <legend>
                    <c:choose>
                        <c:when test="${empty recipeForm.id}"><fmt:message key="recipes.edit.newRecipe"/></c:when>
                        <c:otherwise>${recipeForm.name}</c:otherwise>
                    </c:choose>

                    <form:errors path="*" cssClass="errorBox"/>
                </legend>

                <form:hidden path="id"/>

                <form:label path="name">
                    <fmt:message key="products.edit.name"/><form:errors path="name" cssClass="error"/>
                </form:label>
                <form:input id="focusedInput" path="name" class="input-xlarge" maxlength="255"/>

                <form:label path="amount">
                    <fmt:message key="products.edit.amount"/><form:errors path="amount" cssClass="error"/>
                </form:label>
                <form:input path="amount" autofocus="true" autocomplete="off"/>

                <form:label path="unitId">
                    <fmt:message key="products.edit.unit"/><form:errors path="unitId" cssClass="error"/>
                </form:label>
                <form:select path="unitId" items="${allUnits}"/>

                <form:label path="categoryId">
                    <fmt:message key="products.edit.category"/><form:errors path="categoryId" cssClass="error"/>
                </form:label>
                <form:select path="categoryId" items="${allCategories}"/>

                <form:label path="description">
                    <fmt:message key="products.edit.description"/><form:errors path="description" cssClass="error"/>
                </form:label>
                <form:input path="description" class="input-xxlarge" maxlength="255"/>

                <form:label path="pointsLabel">
                    <fmt:message key="products.edit.points"/><form:errors path="pointsLabel" cssClass="error"/>
                </form:label>
                <form:input readonly="true" path="pointsLabel"/>

                <form:label path="shared">
                    <fmt:message key="products.edit.shared"/><form:errors path="shared" cssClass="error"/>
                </form:label>
                <form:checkbox path="shared"/>

            </fieldset>

            <hr/>

            <table class="table">
                <caption class="text-left">
                    <c:choose>
                        <c:when test="${empty recipeForm.id}">
                            <a class="btn btn-primary pull-right" href="javascript:;"
                               onclick="document.getElementById('form').setAttribute('action', './adddetail1') ;document.getElementById('form').submit();"><fmt:message key="recipes.edit.addProduct"/></a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-primary pull-right" href="javascript:;"
                               onclick="document.getElementById('form').setAttribute('action', './adddetail1?key=' + ${recipeForm.id}) ;document.getElementById('form').submit();"><fmt:message key="recipes.edit.addProduct"/></a>
                        </c:otherwise>
                    </c:choose>
                    <h4><fmt:message key="recipes.edit.products"/></h4>
                </caption>
                <thead>
                <tr>
                    <th><fmt:message key="products.list.amount"/></th>
                    <th><fmt:message key="products.list.unit"/></th>
                    <th><fmt:message key="products.list.name"/></th>
                    <th><fmt:message key="products.list.points"/></th>
                    <th colspan="2"><fmt:message key="recipes.edit.actions"/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${recipeForm.recipeDetailForms}" var="detail">
                    <tr>
                        <td id="td_amount">${detail.amount}</td>
                        <td id="td_unit">${detail.product.unitLabel}</td>
                        <td id="td_edit" class="td"><a
                                href="editdetail?key=${recipeForm.id}&amp;detailKey=${detail.id}">${detail.product.name}</a>
                        </td>

                        <td><span class="badge badge-info">${detail.pointsLabel}</span></td>

                        <td class="td"><a href="removedetail?key=${recipeForm.id}&amp;detailKey=${detail.id}"><i
                                title="Delete" class="icon-remove"></i></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <hr/>

            <button class="btn btn-primary" type="submit"><fmt:message key="action.submit"/></button>
        </form:form>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
<jsp:include page="../scripts.jsp"/>
</body>
</html>
