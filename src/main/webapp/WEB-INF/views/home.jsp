<%@include file="header.jsp" %>
<c:url value="${contextPath}/j_spring_security_logout" var="springLogout"/>
<c:url value="${contextPath }/login" var="loginPage"/>
<script>
	function logout(){
		document.getElementById("logoutForm").submit();
	};
</script>

<body style="
	background-image: linear-gradient(to bottom, #64B5F6 20%, #BBDEFB);
	background-attachment: fixed;
">
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Bank Manager</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <form class="navbar-form navbar-left" role="search" method="POST" action="/manager/employee/comptes/search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Chercher Compte par Mot Clé" name="mc">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
      	
      	<c:choose>
      	<c:when test="${pageContext.request.userPrincipal.name != null }">
      		${pageContext.request.userPrincipal.name} | <a href="javascript:logout()">Logout</a>
      	</c:when>
      	<c:otherwise>
      		<a href="${loginPage }">Login</a>
      	</c:otherwise>
      	</c:choose>
      </ul>
    </div>
    <!-- /.navbar-collapse -->
  </div>
  <!-- /.container-fluid -->
</nav>
<div class="container-fluid">
	<form method="POST" action="${springLogout }" id="logoutForm">
      		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
    </form>
<div class="row">
	<div class="col-md-12" style="text-align: center;">
		<h1>BANK MANAGER</h1>
	</div>
</div>
<c:if test="${flashs != null}" >
<div class="row">
<div class="col-md-4 col-md-offset-4 col-xs-12 col-sm-4 col-sm-offset-4" style="
	padding: 15px;
    background-color: rgba(255,0,0, .4);
    border-radius: 5px;">
	<c:forEach items="${flashs }" var="flash">
		<h5>- ${flash }</h5>
	</c:forEach>
</div>
</div>
</c:if>



