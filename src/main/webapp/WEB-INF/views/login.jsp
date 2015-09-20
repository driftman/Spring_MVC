<%@include file="home.jsp"%>
<c:url value="${contextPath }/j_spring_security_check" var="springLogin"></c:url>
<div class="row">
	<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-12">
		<br>
		<br>
		<form class="form-group" method="POST" action="${springLogin }">
			<label for="username">Username :</label>
			<input type="text" class="form-control" name="username" id="username">
			<br>
			<label for="password">Password :</label>
			<input type="password" class="form-control" name="password" id="password">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.value }"/>
			<br>
			<button type="submit" class="btn btn-primary" aria-label="left line" style="float: right;">
			<span class="glyphicon glyphicon-ok"></span>connect</button>
		</form>
		
	</div>
</div>


<%@ include file="footer.jsp" %>
