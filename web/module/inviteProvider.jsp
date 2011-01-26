<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

My current providers:
<ul>
	<c:forEach var="p" items="${ myProviders }">
		<li>${ p.personName }</li>
	</c:forEach>
</ul>

<hr/>

Invite a new provider to view your record:

<form method="post">

	<select name="userIdToInvite">
		<c:forEach var="p" items="${ allProviders }">
			<option value="${ p.userId }">${ p.personName }</option>
		</c:forEach>
	</select>
	
	<input type="submit"/>

</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>