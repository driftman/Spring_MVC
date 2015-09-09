<%@ include file="../home.jsp" %>
<h5>Les operations qui se sont faites il y a quelques instants : </h5>
	<div class="row">
		<div class="col-md-6 col-md-offset-2 col-sm-12 col-xs-12">
			<table class="table">
				<thead>
					<tr><td>Type Operation</td><td>Date Operation</td><td>Client</td><td>Employee</td><td>Type Compte</td></tr>
				</thead>
				<tbody>
					<c:forEach items="${operations }" var="operation">
						<tr>
						<td>${operation }</td>
						<td>${operation.date}</td>
						<td>${operation.compte.client.coordonnee.nom}, ${operation.compte.client.coordonnee.prenom}</td>
						<td>${operation.employee.coordonnee.nom}, ${operation.employee.coordonnee.prenom}</td>
						<td>${operation.compte}</td></tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

<%@ include file="../footer.jsp" %>