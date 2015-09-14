<%@ include file="../home.jsp" %>
<div class="row">
<div class="col-md-6">
<h2>Ajouter Employee</h2>
<f:form action="ajouter" commandName="employeeModel" method="POST" >
<div class="form-group">
	<table class="table">
		<tr><td>Nom : </td><td><f:input path="nom" type="text"/></td></tr>
		<tr style="color: red; font-weight: bold;"><td></td><td><f:errors path="nom" ></f:errors></td></tr>
		<tr><td>Prenom : </td><td><f:input path="prenom" type="text"/></td></tr>
		<tr style="color: red; font-weight: bold;"><td></td><td><f:errors path="prenom" ></f:errors></td></tr>
		<tr><td>Age : </td><td><f:input path="age" type="decimal"/></td></tr>
		<tr style="color: red; font-weight: bold;"><td></td><td><f:errors path="age" ></f:errors></td></tr>
		<tr><td>Email : </td><td><f:input path="email" type="email"/></td></tr>
		<tr style="color: red; font-weight: bold;"><td></td><td><f:errors path="email" ></f:errors></td></tr>
		<tr><td>Ville : </td><td><f:input path="ville" type="text"/></td></tr>
		<tr style="color: red; font-weight: bold;"><td></td><td><f:errors path="ville" ></f:errors></td></tr>
		<tr><td>Quartier : </td><td><f:input path="quartier" type="text"/></td></tr>
		<tr style="color: red; font-weight: bold;"><td></td><td><f:errors path="quartier" ></f:errors></td></tr>
		<tr><td>Code Postale : </td><td><f:input path="code_postale" type="decimal"/></td></tr>
		<tr style="color: red; font-weight: bold;"><td></td><td><f:errors path="code_postale" ></f:errors></td></tr>
		<tr><td>Numero Lieu : </td><td><f:input path="numero_lieu" type="decimal"/></td></tr>
		<tr style="color: red; font-weight: bold;"><td></td><td><f:errors path="numero_lieu" ></f:errors></td></tr>
		<tr><td>Nom d'utilisateur : </td><td><f:input path="username" type="text"/></td></tr>
		<tr style="color: red; font-weight: bold;"><td></td><td><f:errors path="username" ></f:errors></td></tr>
		<tr><td>Password : </td><td><f:input path="password" type="password"/></td></tr>
		<tr style="color: red; font-weight: bold;"><td></td><td><f:errors path="password" ></f:errors></td></tr>
		<tr><td>Secret Pass : </td><td><f:input path="secret_pass" type="text"/></td></tr>
		<tr style="color: red; font-weight: bold;"><td></td><td><f:errors path="secret_pass" ></f:errors></td></tr>
		
		<tr><td><button class="btn btn-danger">Annuler</button></td><td><button type="submit" class="btn btn-primary">Envoyer</td> </tr>
		
	</table>
</div>
</f:form>
</div>
</div>
<%@ include file="../footer.jsp" %>