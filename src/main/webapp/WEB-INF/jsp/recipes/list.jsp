<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
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
    <link rel="stylesheet" href="<c:url value="/assets/css/custom.css"/>">

    <script src="<c:url value="/assets/js/jquery-2.0.0.min.js"/>"></script>
    <script>
        $(document).ready(function(){
            $('#nav_recipes').attr('class', 'active');
        });
    </script>

</head>

<body>

<div class="container">
    <jsp:include page="../navigation.jsp"/>


    <div class="row-fluid">
        <!-- left panel / infos -->
        <div id="sidebar" class="">
            <ul class="nav nav-pills">
                <c:forEach items="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z" var="letter">
                    <li>
                        <a title="Search recipes starting with a '${letter}'"
                           href="${pageContext.servletContext.contextPath}/recipes/list?queryName=${letter}%25">${letter}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <!--  main panel  -->
        <div id="main" class="">

            <div class="">
                <a class="btn btn-primary pull-right" href="./edit"><fmt:message key="recipes.list.newRecipe"/></a>

                <form id="form" class="form-inline" action="./list" method="get">
                    <fieldset>
                        <input id="focusedInput" type="text" class="input-large" name="queryName" value="${queryName}"
                               placeholder="recipe name"
                               autofocus="true"/>
                        <select name="category" value="${category}" placeholder="category">
                            <option value="">---</option>
                            <c:forEach items="${allCategories}" var="cat">
                                <option value="${cat.key}">${cat.value}</option>
                            </c:forEach>
                        </select>
                        <button type="submit" class="btn"><fmt:message key="action.search"/></button>
                    </fieldset>
                </form>
            </div>

            <table class="table table-hover">
                <thead>
                <tr>
                    <th><fmt:message key="products.list.name"/></th>
                    <th><fmt:message key="products.list.points"/></th>
                    <th class="hidden-phone"><fmt:message key="products.list.category"/></th>
                    <th class="hidden-phone"><fmt:message key="products.list.description"/></th>
                    <th>Action</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${recipes}" var="recipe">
                    <tr>
                        <td><a href="./edit?key=${recipe.id}">${recipe.name}</a></td>
                        <td><span class="badge badge-info">${recipe.pointsLabel}</span></td>
                        <td class="hidden-phone">${recipe.categoryLabel}</td>
                        <td class="hidden-phone">${recipe.description}</td>
                        <td class="td"><a href="./delete?key=${recipe.id}"><i title="Delete"
                                                                              class="icon-remove"></i></a></td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>


            <c:if test="${pageCount > 1}">
                <div class="pagination pagination-centered">
                    <ul>
                        <c:choose>
                            <c:when test="${currentPage == 1}">
                                <li class="disabled"><a href="#"><fmt:message key="action.previous"/></a></li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="${pageContext.servletContext.contextPath}/recipes/list?page=${currentPage-1}"><fmt:message key="action.previous"/></a>
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
                                    <li>
                                        <a href="${pageContext.servletContext.contextPath}/recipes/list?page=${idx}">${idx}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${currentPage >= pageCount}">
                                <li class="disabled"><a href="#"><fmt:message key="action.next"/></a></li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="${pageContext.servletContext.contextPath}/recipes/list?page=${currentPage+1}"><fmt:message key="action.next"/></a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </c:if>
        </div>
    </div>
    <jsp:include page="../footer.jsp"/>
    <jsp:include page="../scripts.jsp"/>
</body>
</html>