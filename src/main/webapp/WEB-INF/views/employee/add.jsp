<%@ include file="../home.jsp" %>
<div class="row">
<div class="col-md-6">
<h2>Ajouter Employee</h2>
<f:form action="employee/ajouter" commandName="employeeModel" method="POST" >
<div class="form-group">
	<table class="table">
		<tr><td>Nom : </td><td><f:input path="coordonnee.nom" type="text"/></td></tr>
		<tr><td>Prenom : </td><td><f:input path="coordonnee.prenom" type="text"/></td></tr>
		<tr><td>Age : </td><td><f:input path="coordonnee.age" type="decimal"/></td></tr>
		<tr><td>Email : </td><td><f:input path="coordonnee.email" type="email"/></td></tr>
		<tr><td>Ville : </td><td><f:input path="adresse.ville" type="text"/></td></tr>
		<tr><td>Quartier : </td><td><f:input path="adresse.quartier" type="text"/></td></tr>
		<tr><td>Code Postale : </td><td><f:input path="adresse.code_postale" type="decimal"/></td></tr>
		<tr><td>Numero Lieu : </td><td><f:input path="adresse.numero_lieu" type="decimal"/></td></tr>
		<tr><td><button class="btn btn-danger">Annuler</button></td><td><button type="submit" class="btn btn-primary">Envoyer</td> </tr>
		
	</table>
</div>
</f:form>
</div>
</div>
<%@ include file="../footer.jsp" %>