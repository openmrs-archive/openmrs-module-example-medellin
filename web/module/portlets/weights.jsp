<%@ include file="/WEB-INF/template/include.jsp" %>

<div class="boxHeader">
    Pesos historicos del paciente
</div>
<div class="box">
    <table>
		<c:forEach var="obs" items="${ model.patientObs }">
		   <c:if test="${ obs.concept.conceptId == 5089 }">
		       <tr>
		          <td><openmrs:formatDate date="${ obs.obsDatetime }"/></td>
		          <td>${ obs.valueNumeric }</td>
		       </tr>
		   </c:if>
		</c:forEach>
	</table>
</div>