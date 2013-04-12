<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>EasyFit - Recipes</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="EasyFit, track what you eat and stay fit !">
    <meta name="author" content="Hans Fortemaison">

    <!-- Le styles -->
    <link href="<c:url value="/assets/css/bootstrap.css"/>" rel="stylesheet">

    <link href="<c:url value="/assets/css/bootstrap-responsive.css"/>" rel="stylesheet">

    <link href="<c:url value="/assets/js/google-code-prettify/prettify.css"/>" rel="stylesheet">

</head>
<body onload="javascript:; document.getElementById('nav_recipes').setAttribute('class', 'active');">

<div class="container">
    <jsp:include page="../navigation.jsp"/>

    <a class="btn btn-primary pull-right" href="./edit">New recipe</a>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>Name</th>
            <th>Points</th>
            <span class="hidden-phone">
                <th>Category</th>
                <th>Description</th>
            </span>
            <th>Action</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${recipes}" var="recipe">
            <tr>
                <td><a href="./edit?key=${recipe.id}">${recipe.name}</a></td>
                <td><span class="badge badge-info">${recipe.pointsLabel}</span></td>
                <span class="hidden-phone">
                    <td>${recipe.categoryLabel}</td>
                    <td>${recipe.description}</td>
                </span>
                <td class="td"><a href="./delete?key=${recipe.id}"><i title="Delete" class="icon-remove"></i></a></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>


    <c:if test="${pageCount >= 1}">
        <div class="pagination pagination-centered">
            <ul>
                <c:choose>
                    <c:when test="${currentPage == 1}">
                        <li class="disabled"><a href="#">Prev</a></li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/recipes/list?page=${currentPage-1}">Prev</a>
                        </li>
                    </c:otherwise>
                </c:choose>

                <c:forEach var="idx" begin="1" end="${pageCount}">
                    <c:choose>
                        <c:when test="${currentPage == idx}">
                            <li class="active"><a
                                    href="${pageContext.servletContext.contextPath}/recipes/list?page=${idx}">${idx}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${pageContext.servletContext.contextPath}/recipes/list?page=${idx}">${idx}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:choose>
                    <c:when test="${currentPage >= pageCount}">
                        <li class="disabled"><a href="#">Next</a></li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/recipes/list?page=${currentPage+1}">Next</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </c:if>
</div>
<jsp:include page="../footer.jsp"/>
<jsp:include page="../scripts.jsp"/>
</body>
</html>