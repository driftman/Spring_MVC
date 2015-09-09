<%@ include file="../home.jsp" %>
<div class="row">
<div class="col-md-6">
<h2>Demande Client</h2>
<f:form action="client/demande" commandName="clientModel" method="POST" >
<div class="form-group">
	<table class="table">
		<tr><td>Nom : </td><td><f:input path="coordonnee.nom" type="text" class="form-control"/></td></tr>
		<tr><td>Prenom : </td><td><f:input path="coordonnee.prenom" type="text" class="form-control"/></td></tr>
		<tr><td>Age : </td><td><f:input path="coordonnee.age" type="decimal" class="form-control"/></td></tr>
		<tr><td>Email : </td><td><f:input path="coordonnee.email" type="email" class="form-control"/></td></tr>
		<tr><td>Ville : </td><td><f:input path="adresse.ville" type="text" class="form-control"/></td></tr>
		<tr><td>Quartier : </td><td><f:input path="adresse.quartier" type="text" class="form-control"/></td></tr>
		<tr><td>Code Postale : </td><td><f:input path="adresse.code_postale" type="decimal" class="form-control"/></td></tr>
		<tr><td>Numero Lieu : </td><td><f:input path="adresse.numero_lieu" type="decimal" class="form-control"/></td></tr>
		<tr><td>Lettre Motivation : </td><td><f:textarea path="situation.lettreMotivation" class="form-control"/></td></tr>
		<tr><td>Fonctionnaire : </td><td><f:checkbox path="situation.fonctionnaire" class="form-control"/></td></tr>
		<tr><td>Salaire Fix : </td><td><f:checkbox path="situation.salaireFix" class="form-control"/></td></tr>
		<tr><td>Salaire Mensuel : </td><td><f:input path="situation.salaireMensuel" type="decimal" class="form-control"/></td></tr>
		<tr><td>Marié : </td><td><f:checkbox path="situation.marie" class="form-control"/></td></tr>
		<tr><td><button class="btn btn-danger">Annuler</button></td><td><button type="submit" class="btn btn-primary">Envoyer</td> </tr>
		
	</table>
</div>
</f:form>
</div>
</div>
<%@ include file="../footer.jsp" %>
