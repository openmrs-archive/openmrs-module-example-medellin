<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />

<script>
$j(document).ready(function() {
	$j('.error').not('#openmrs_dwr_error')
	       .hide()
	       .fadeIn('slow');
	
	$j('#username').blur(function() {
		$j('#usernameCheck').html("checking...").css('color', 'black');
		$j.getJSON('q/checkUsername.json',
				   { "username": $j('#username').val() },
				   function(data) {
					   if (data.result) {
						   $j('#usernameCheck').html("no disponible!").css('color', 'red');
					   } else {
						   $j('#usernameCheck').html("OK").css('color', 'green');;
					   }
				   });
	});
	
	var nextFriend = 1;
	$j('#friend0').show();
	$j('#anotherFriend').click(function() {
		$j('#friend' + nextFriend).show();
		nextFriend += 1;
		if (nextFriend > 19)
			nextFriend = 19;
	});
})
</script>

<h2>Create Vitalbox User</h2>

<form:form modelAttribute="userDetails" method="post">
	Usuario<br/>
	<form:input path="username" id="username"/> <form:errors path="username" cssClass="error"/>
	<span id="usernameCheck"></span>
	<br/>
	
	Nombre<br/>
    <form:input path="firstName"/> <form:errors path="firstName" cssClass="error"/><br/>
    
    Segundo Nombre<br/>
    <form:input path="secondName"/> <form:errors path="secondName" cssClass="error"/><br/>
    
    Apellido 1<br/>
    <form:input path="lastName"/> <form:errors path="lastName" cssClass="error"/><br/>
    
    Apellido 2<br/>
    <form:input path="lastName2"/> <form:errors path="lastName2" cssClass="error"/><br/>
    
    Correo<br/>
    <form:input path="email"/> <form:errors path="email" cssClass="error"/><br/>
    
    Confirmar Correo<br/>
    <form:input path="confirmEmail"/> <form:errors path="confirmEmail" cssClass="error"/><br/>
	
	Clave<br/>
	<form:password path="password"/> <form:errors path="password" cssClass="error"/><br/>
	
	Confirmar clave<br/>
	<form:password path="confirmPassword"/> <form:errors path="confirmPassword" cssClass="error"/><br/>
	
	...<br/>
	
	Sexo<br/>
	<form:radiobutton path="gender" value="M" label="Masculino"/>
	<form:radiobutton path="gender" value="F" label="Feminino"/>
	<form:errors path="gender" cssClass="error"/><br/>
	
	Fecha Nacimiento<br/>
	<input type="text" name="birthdate" value="<openmrs:formatDate date="${userDetails.birthdate}"/>" onClick="showCalendar(this,60)"/>
	<form:errors path="birthdate" cssClass="error"/><br/>
	
	Ciudadania<br/>
	<form:select path="citizenship">
	   <form:option label="Colombia" value="co"/>
	   <form:option label="EEUU" value="us"/>
	</form:select>
	<br/>
	
	<%--
	Lugar<br/>
	<openmrs_tag:locationTree formFieldName="location" selectableTags="Institution" startFromTag="City"/>
	--%>
	
	Cuentale a un amigo<br/>
    <c:forEach var="i" begin="0" end="19">
        <div id="friend${ i }" style="display: none">
            <input type="text"/>
        </div>
    </c:forEach>
    <input type="button" id="anotherFriend" value="agregar mas"/>

    <br/>		
	<br/>
	<input type="submit" value="Create My Account"/>
</form:form>

<%@ include file="/WEB-INF/template/footer.jsp"%>