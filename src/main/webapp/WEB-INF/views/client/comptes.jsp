<%@ include file="../home.jsp" %>



<c:choose>
	<c:when test="${not empty comptes}">
		<div class="row">
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-xs-12">
				<table class="table">
					<thead>
						<tr><th>Code Compte</th><th>Type Compte</th><th>Solde</th><th>Date Creation</th><th>Actions</th></tr>
					</thead>
					<tbody>
						<c:forEach items="${comptes }" var="compte">
							<tr><td>${compte.codeCompte }</td><td>${compte}</td><td>${compte.soldeDepart }</td><td>${compte.dateCreation}</td>
							<td><a href="<c:url value="/employee/comptes/${compte.codeCompte }"></c:url>" class="btn btn-primary">Details</a></td> </tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:when>
</c:choose>

<%@ include file="../footer.jsp" %>