<%@ include file="../home.jsp"   %>
	<div class="row">
	<div class="col-md-6 col-sm-12 col-xs-12">
		<h3>Code Compte : ${compte.codeCompte }</h3>
		<p><b>Solde :</b> ${compte.soldeDepart } - MAD</p>
		<p><b>Date Creation :</b> ${compte.dateCreation}</p>
		<p><b>Type de compte :</b> ${compte}</p>
		<p><b>Crée par :</b> ${compte.employee.coordonnee.nom}, ${compte.employee.coordonnee.prenom}</p>
		<p><b>Appartient à :</b> ${compte.client.coordonnee.nom}, ${compte.client.coordonnee.prenom}</p>
		<table class="table">
			<thead>
				<tr><th>Type d'operation</th><th>Somme</th><th>Date</th><th>Employee</th></tr>
			</thead>
			<tbody>
				<c:forEach items="${compte.operations }" var="operation">
					<tr><td>${operation}</td><td>${operation.somme } - MAD</td><td>${operation.date}</td><td>${operation.employee.coordonnee.nom }</td></tr>
				</c:forEach>
			</tbody>
		</table>
		
	</div>
	</div>
<%@ include file="../footer.jsp" %>