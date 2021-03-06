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

    <c:choose>
        <c:when test="${finderMode}">
            <c:set var="action" value="./adddetail1?"/>
            <c:set var="formMethod" value="post"/>
        </c:when>
        <c:otherwise>
            <c:set var="action" value="./list?"/>
            <c:set var="formMethod" value="get"/>
        </c:otherwise>
    </c:choose>

    <script src="<c:url value="/assets/js/jquery-2.0.0.min.js"/>"></script>
    <script language="javascript">

        function replaceMain(data) {
            var newData = $.parseHTML(data);
            newData = $("#main", newData);
            $("#main").replaceWith(newData);
        }

        function doPostFromLetters(aLetter) {
            var queryStr = aLetter + "%";
            <c:choose>
            <c:when test="${finderMode}">
            $.post("${action}", {queryName: queryStr},
                    function (data) {
                        replaceMain(data);
                    }
            );
            </c:when>
            <c:otherwise>
            $.get("./list", {queryName: queryStr},
                    function (data) {
                        replaceMain(data);
                    }
            );
            </c:otherwise>
            </c:choose>
        }

        function goToPage(pageIdx) {
            <c:choose>
            <c:when test="${finderMode}">
            $.post("${action}", {page: pageIdx},
                    function (data) {
                        replaceMain(data);
                    }
            );
            </c:when>
            <c:otherwise>
            $.get("./list", {page: pageIdx},
                    function (data) {
                        replaceMain(data);
                    }
            );
            </c:otherwise>
            </c:choose>
        }

        function queryProduct() {
            <c:choose>
            <c:when test="${finderMode}">
            $.post("${action}", {queryName: queryName},
                    function (data) {
                        replaceMain(data);
                    }
            );
            </c:when>
            <c:otherwise>
            $.get("./list",
                    function (data) {
                        replaceMain(data);
                    }
            );
            </c:otherwise>
            </c:choose>
        }

        function chooseProduct(productId) {
            <c:choose>
            <c:when test="${finderMode}">
            var form = $('#form');
            form.attr("method", "${formMethod}");

            var button = $("button", form);
            button.attr("type", "submit");
            button.removeAttr("onclick");
            form.attr('action', './adddetail2?productKey=' + productId);
            form.submit();
            </c:when>
            <c:otherwise>
            window.location = './edit?key=' + productId;
            </c:otherwise>
            </c:choose>
        }

        $(document).ready(function(){
            <c:choose>
            <c:when test="${finderMode}">
            $("#form").attr("method", "post");
            $('#nav_agenda').attr('class', 'active').focus();

            <c:if test="${hideNavigation}">
            $("#sidebar").hide();
            $("#form").hide();
            $("#paging").hide();
            </c:if>
            </c:when>
            <c:otherwise>
            $('#nav_products').attr('class', 'active').focus();
            </c:otherwise>
            </c:choose>
        });

    </script>
</head>
<body>
<div class="container" id="container">
    <jsp:include page="../navigation.jsp"/>

    <div id="main" class="row-fluid">
        <!-- left panel / infos -->
        <div id="sidebar" class="">
            <ul class="nav nav-pills">
                <c:forEach items="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z" var="letter">
                    <li>
                        <a title="Search products starting with a '${letter}'"
                           href="javascript:doPostFromLetters('${letter}')">${letter}</a></li>
                </c:forEach>
            </ul>
        </div>

        <!--  main panel  -->
        <div class="">

            <c:if test="${!finderMode}">
                <a class="btn btn-primary pull-right" href="./edit"><fmt:message key="products.list.newProduct"/></a>
            </c:if>

            <form id="form" class="form-inline" method="${formMethod}">
                <fieldset>
                    <input id="focusedInput" type="text" class="input-large" name="queryName" value="${queryName}"
                           placeholder="product name"
                           autofocus="true"/>
                    <select name="category" value="${category}" placeholder="category">
                        <option value="">---</option>
                        <c:forEach items="${allCategories}" var="cat">
                            <option value="${cat.key}">${cat.value}</option>
                        </c:forEach>
                    </select>
                    <button onclick="javascript:queryProduct()" class="btn"><fmt:message key="products.list.search"/></button>
                </fieldset>
            </form>

            <table class="table table-hover">
                <thead>
                <tr>
                    <th><fmt:message key="products.list.amount"/></th>
                    <th><fmt:message key="products.list.unit"/></th>
                    <th><fmt:message key="products.list.name"/></th>
                    <th><fmt:message key="products.list.points"/></th>
                    <th><fmt:message key="products.list.maxPoints"/></th>
                    <th class="hidden-phone"><fmt:message key="products.list.category"/></th>
                    <th class="hidden-phone"><fmt:message key="products.list.description"/></th>
                    <c:if test="${!finderMode}">
                        <th><fmt:message key="products.list.action"/></th>
                    </c:if>
                </tr>
                </thead>

                <tbody id="tbody">
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td>${product.amountLabel}</td>
                        <td>${product.unitLabel}</td>
                        <td>
                            <a href="javascript:chooseProduct('${product.id}')">${product.name}</a>
                        </td>
                        <td><span class="badge badge-info">${product.pointsLabel}</span></td>
                        <td><span class="badge badge-success">${product.maxPoints}</span></td>
                        <td class="hidden-phone">${product.categoryLabel}</td>
                        <td class="hidden-phone">${product.description}</td>
                        <c:if test="${!finderMode}">
                            <td class="td">
                                <a href="./delete?key=${product.id}">
                                    <i title="Delete" class="icon-remove"></i>
                                </a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <!--  Pagination  -->
            <div id="paging">
                <c:if test="${pageCount > 1}">
                    <div class="pagination pagination-centered">
                        <ul>
                            <c:choose>
                                <c:when test="${currentPage == 1}">
                                    <li id="li_page_previous" class="disabled"><a href="#"><fmt:message key="action.previous"/></a></li>
                                </c:when>
                                <c:otherwise>
                                    <li id="li_page_previous">
                                        <a href="javascript:goToPage(${currentPage-1})"><fmt:message key="action.previous"/></a>
                                    </li>
                                </c:otherwise>
                            </c:choose>

                            <c:forEach var="idx" begin="1" end="${pageCount}">
                                <c:choose>
                                    <c:when test="${currentPage == idx}">
                                        <li id="li_page_${idx}" class="active">
                                            <a href="javascript:goToPage(${idx})">${idx}</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li id="li_page_${idx}">
                                            <a href="javascript:goToPage(${idx})">${idx}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <c:choose>
                                <c:when test="${currentPage >= pageCount}">
                                    <li id="li_page_next" class="disabled"><a href="#"><fmt:message key="action.next"/></a></li>
                                </c:when>
                                <c:otherwise>
                                    <li id="li_page_next">
                                        <a href="javascript:goToPage(${currentPage+1})"><fmt:message key="action.next"/></a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
<jsp:include page="../scripts.jsp"/>
</body>
</html>