<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>EasyFit - Products</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="EasyFit, track what you eat and stay fit !">
    <meta name="author" content="Hans Fortemaison">

    <!-- Le styles -->
    <link href="<c:url value="/assets/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/css/bootstrap-responsive.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/js/google-code-prettify/prettify.css"/>" rel="stylesheet">

</head>
<body onload="javascript:; document.getElementById('nav_agenda').setAttribute('class', 'active'); document.getElementById('focusedInput').focus()">

<div class="container">
    <jsp:include page="../navigation.jsp"/>

    <form id="form" class="form-inline" method="post" action="./adddetail1?">
        <input id="focusedInput" type="text" name="queryName" value="${queryName}" placeholder="product name"
               autofocus="true"/>
        <select name="category" value="${category}">
            <option value="">---</option>
            <c:forEach items="${allCategories}" var="cat">
                <option value="${cat.key}">${cat.value}</option>
            </c:forEach>
        </select>
        <button type="submit" class="btn">Search</button>
        </fieldset>
    </form>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>Amount</th>
            <th>Unit</th>
            <th>Name</th>
            <th>Points</th>
            <th>Max Points</th>
            <span class="hidden-phone">
                <th>Description</th>
            </span>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.amount}</td>
                <td>${product.unitLabel}</td>
                <td>
                    <a href="javascript:;"
                       onclick="document.getElementById('form').setAttribute('action', './adddetail2?productKey=${product.id}') ;document.getElementById('form').submit();">${product.name}</a>
                </td>
                <td><span class="badge badge-info">${product.pointsLabel}</span></td>
                <td><span class="badge badge-success">${product.maxPoints}</span></td>
                <span class="hidden-phone">
                    <td>${product.description}</td>
                </span>
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
                            <a href="${pageContext.servletContext.contextPath}/products/list?page=${currentPage-1}">Prev</a>
                        </li>
                    </c:otherwise>
                </c:choose>

                <c:forEach var="idx" begin="1" end="${pageCount}">
                    <c:choose>
                        <c:when test="${currentPage == idx}">
                            <li class="active"><a
                                    href="${pageContext.servletContext.contextPath}/products/list?page=${idx}">${idx}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${pageContext.servletContext.contextPath}/products/list?page=${idx}">${idx}</a>
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
                            <a href="${pageContext.servletContext.contextPath}/products/list?page=${currentPage+1}">Next</a>
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