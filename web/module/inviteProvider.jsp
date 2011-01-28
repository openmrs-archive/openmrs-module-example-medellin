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

<br/>
<br/>
<br/>
<br/>
<br/>
<br/>

<openmrs:htmlInclude file="/dwr/engine.js" />
<!-- <openmrs:htmlInclude file="/dwr/util.js" /> -->
<openmrs:htmlInclude file="/dwr/interface/DWRExample.js" />

<script>
$j(document).ready(function() {
    $j('#username').change(function() {
    	DWRExample.userExists($j('#username').val(), function(data) {
    		if (data)
    			$j('#username-error').html('Already in use!');
            else
                $j('#username-error').html('Okay');
    	});
    	
    	/*
    	String ajaxUrl = 'checkIfUserExists.form?username=' + $j('#username').val();
    	$j.getJSON(ajaxUrl, function(data) {
    	    if (data.exists == 'true')
    	    	$j('#username-error').html('Already in use!');
    	    else
    	    	$j('#username-error').html('Okay');
    	});
    	*/
    })
});
</script>

<input type="text" id="username"/>
<span id="username-error"></span>

<%@ include file="/WEB-INF/template/footer.jsp"%>