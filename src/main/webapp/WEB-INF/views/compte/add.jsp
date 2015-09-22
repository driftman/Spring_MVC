<%@ include file="../home.jsp" %>

	<c:if test="${not empty operation && not empty compte }">
		<div class="row">
			<div class="col-md-6 col-sm-12 col-xs-12">
				<h4 style="color: white; font-weight: 700;">Compte</h4><br>
				<table class="table">
					<thead>
						<tr><th>Code </th><th>Solde</th><th>Crée le</th>
						<th>Type </th><th>Crée par</th><th>Appartient à</th></tr>
					</thead>
					<tbody>
						<tr><td>${compte.codeCompte }</td><td>${compte.soldeDepart } - MAD</td>
						<td>${compte.dateCreation}</td>
						<td> ${compte}</td>
						<td>${compte.employee.coordonnee.nom}, ${compte.employee.coordonnee.prenom}</td>
						<td>${compte.client.coordonnee.nom}, ${compte.client.coordonnee.prenom}</td></tr>
					</tbody>
				</table>
			</div>
			</div>
			<div class="row">
			<div class="col-md-6 col-sm-12 col-xs-12">
				<h4 style="color: white; font-weight: 700;">Operation</h4><br>
				<table class="table">
					<thead>
						<tr><th>Type</th><th>Somme</th><th>Date</th><th>Employee</th></tr>
					</thead>
					<tbody>
						<tr><td>${operation}</td><td>${operation.somme } - MAD</td><td>${operation.date}</td><td>${operation.employee.coordonnee.nom }</td></tr>
					</tbody>
				</table>
			</div>
			</div>
	</c:if>
	
	<c:if test="${not empty compteModel }">
		<div class="row">
		<div class="col-md-6">
		<h3 style="color: white; font-weight: 700;">Ajouter Compte</h3>
		<f:form action="add" commandName="compteModel" method="POST" >
		<div class="form-group">
			<table class="table">
			
				<tr>
				
					<td>Type de compte : </td>
					<td>
						<f:select path="typeCompte" cssClass="form-control" >
							<f:option value="Compte Courant"></f:option>
							<f:option value="Compte Epargne"></f:option>
						</f:select>
					</td>
				</tr>
				
				<tr>
					<td>Propriétaire</td>
					<td><f:input path="ownerId"></f:input></td>
				</tr>
				
				<tr>
					<td>Solde départ</td>
					<td>
						<div class="input-group" style="border: none; ;">
					      <div class="input-group-addon" style="border-radius: 0; background: white; border: medium none;">MAD</div>
					      <f:input path="amount" type="number" class="form-control" id="somme" name="somme" style="border: none;"></f:input>
					      <div class="input-group-addon" style="border-radius: 0; background: white; border: medium none;">.00</div>
			   			 </div>
					</td>
				</tr>
				
				<tr>
					<td></td>
					<td>
						<button class="btn btn-primary" type="submit">valider</button>
					</td>
				</tr>
			</table>
		</div>
		</f:form>
		</div>
		</div>
	</c:if>

<%@ include file="../footer.jsp" %>


