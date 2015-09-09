<%@ include file="../home.jsp" %>
<div class="row">
	<div class="col-md-6 col-sm-12 col-xs-12">
	<br><br><br>
	<h3>Effectuer un virement : </h3>
	<form class="form-group" method="POST" action="/manager/transaction/add">
	
		<label for="from" >De :</label>
		<input type="text" name="from" id="from" class="form-control" placeholder="code compte du créditeur">
		
		<label for="to" >A :</label>
		<input type="text" name="to" id="to" class="form-control" placeholder="code compte du débiteur">
		
		<label for="somme">Somme (en Dirhams) :</label>
	    <div class="input-group">
	      <div class="input-group-addon">MAD</div>
	      <input type="number" class="form-control" id="somme" name="somme" placeholder="somme de la transaction">
	      <div class="input-group-addon">.00</div>
	    </div>
	    <label for="type">Type transfert :</label>
	    <select class="form-control" name="typeTransfert">
		  <option>Chèque</option>
		  <option>Cash</option>
		  <option>Interne</option>
		</select>
		<br>
		<button type="submit" class="btn btn-primary" style="float: right;"><span class="glyphicon glyphicon-ok"></span> Valider </button>
	</form>
	</div>
</div>