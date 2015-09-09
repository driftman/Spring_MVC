<%@include file="header.jsp" %>
<body>
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
        <li><a href="#">Se Déconnecter</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="container-fluid">
<div class="row">
	<div class="col-md-12" style="text-align: center;">
		<h1>BANK MANAGER</h1>
	</div>
</div>
<c:if test="${flashs != null}" >
<div class="row">
<div class="col-md-2 col-md-offset-10 col-xs-12 col-sm-6" style="
	padding: 15px;
    background-color: rgba(255,0,0, .4);
    border-radius: 5px;">
	<c:forEach items="${flashs }" var="flash">
		<h5>- ${flash }</h5>
	</c:forEach>
</div>
</div>
</c:if>



