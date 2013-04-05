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
                <a class="brand" href="#">EasyFit</a>

                <div class="nav-collapse collapse navbar-responsive-collapse">
                    <ul class="nav">
                        <li id="nav_agenda"><a
                                href="${pageContext.servletContext.contextPath}/consumptions/list">Agenda</a></li>
                        <li id="nav_recipes"><a
                                href="${pageContext.servletContext.contextPath}/recipes/list">Recipes</a></li>
                        <li id="nav_products"><a
                                href="${pageContext.servletContext.contextPath}/products/list">Products</a></li>
                        <li id="nav_categories"><a href="${pageContext.servletContext.contextPath}/categories/list">Categories</a>
                        </li>
                        <li id="nav_units"><a href="${pageContext.servletContext.contextPath}/units/list">Units</a></li>
                        <li id="nav_users"><a href="${pageContext.servletContext.contextPath}/users/list">Users</a></li>
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

