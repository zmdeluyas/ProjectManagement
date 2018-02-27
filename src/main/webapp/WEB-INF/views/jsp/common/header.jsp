<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/resources/core/css/images/myAvatar.png" var="myAvatar" />
<nav class="navbar navbar-default navbar-static-top main header">
    <!-- <div class="container"> -->
      <div class="navbar-header">
        <a id="logo" class="navbar-brand" href="#" style="color: white">
        	TRUE-CI
        </a>
      </div>
      <div id="mainnavbar" class="navbar-collapse collapse header white">
        <ul class="nav navbar-nav navbar-left">
          <li class="dropdown">
            <a id="header-menu" href="#" class="dropdown-toggle hide" data-toggle="dropdown" role="button" aria-expanded="false">
            	<span class="glyphicon glyphicon-menu-hamburger white"></span>
            </a>
            <ul class="dropdown-menu" role="menu">
              <li><a id="home" href="#"><span class="glyphicon glyphicon-home"></span> Home</a></li>
              <li><a id="logout" href="#"><span class="glyphicon glyphicon-off"></span> Logout</a></li>
              <li><a id="back" href="#" class="hide"><span class="glyphicon glyphicon-chevron-left"></span> Back</a></li>
            </ul>
          </li>
        </ul>
        <ul class="nav navbar-nav navbar-right" style="margin-right: 20px">
        	<li><h4 id="username-header" class="hide">Project Manager Name</h4></li>
        	<li><img id="avatar" alt="My Avatar" src="${myAvatar}"  class="img-responsive img-circle avatar hide"></li>
        </ul>
      </div>
      <!--/.nav-collapse -->
    <!-- </div> -->
    <!--/.container-fluid -->
</nav>