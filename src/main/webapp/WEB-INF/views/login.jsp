<%@include file="home.jsp"%>

<div class="row">
	<div class="col-md-4 col-md-offset-4">
		<br>
		<br>
		<form class="form-group" method="POST" action="login">
			<label for="username">Username :</label>
			<input type="text" class="form-control" name="username" id="username">
			<br>
			<label for="password">Password :</label>
			<input type="password" class="form-control" name="password" id="password">
			<br>
			<div class="checkbox">
			    <label>
			      <input type="checkbox" name="employee" value="checked"> Employee
			    </label>
			  </div>
			<button type="submit" class="btn btn-primary" aria-label="left line" style="float: right;">
			<span class="glyphicon glyphicon-ok"></span>
			connect</button>
		</form>
	</div>
</div>
<%@ include file="footer.jsp" %>
