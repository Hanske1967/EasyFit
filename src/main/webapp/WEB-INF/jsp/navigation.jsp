<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="masthead">
    <h2>EasyFit
        <small>Track what you eat and stay fit !</small>
    </h2>

    <div class="navbar">
        <div class="navbar-inner">
            <div class="container">

                <!-- .btn-navbar is used as the toggle for collapsed navbar content -->
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>

                <!-- Be sure to leave the brand out there if you want it shown -->
                <a class="brand" href="${pageContext.servletContext.contextPath}/home">EasyFit</a>

                <div class="nav-collapse collapse navbar-responsive-collapse">
                    <ul class="nav">
                        <li id="nav_agenda"><a
                                href="${pageContext.servletContext.contextPath}/consumptions/list"><fmt:message key="navigation.agenda"/></a></li>
                        <li id="nav_weights"><a
                                href="${pageContext.servletContext.contextPath}/weights/list"><fmt:message key="navigation.weights"/></a></li>
                        <li id="nav_recipes"><a
                                href="${pageContext.servletContext.contextPath}/recipes/list"><fmt:message key="navigation.recipes"/></a></li>
                        <li id="nav_products"><a
                                href="${pageContext.servletContext.contextPath}/products/list"><fmt:message key="navigation.products"/></a></li>
                        <li id="nav_categories"><a href="${pageContext.servletContext.contextPath}/categories/list"><fmt:message key="navigation.categories"/></a>
                        </li>
                        <li id="nav_units"><a href="${pageContext.servletContext.contextPath}/units/list"><fmt:message key="navigation.units"/></a></li>
                        <li id="nav_users"><a href="${pageContext.servletContext.contextPath}/users/list"><fmt:message key="navigation.users"/></a></li>
                    </ul>
                    <ul class="nav pull-right">
                        <li><a href="${pageContext.servletContext.contextPath}//home">${userForm.userName}</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- /.navbar -->

</div>

