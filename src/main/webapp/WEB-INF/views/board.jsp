<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="./common/header.jsp"%>

<main class="main-section">
<section class="admin">

	<%@include file="./common/admin-side.jsp"%>

	<div class="admin-main">
		<div class="admin-main-header">
			<h1>Current Year Report: ${currentYearReport.year}</h1>
			<h1>Settings</h1>

		

	
		

		</div>

	<form:form modelAttribute="yearReport" method="GET"
			action="${contextRoot}/admin/newcurrentyearreport">		
				
				<label for="year">Year:</label>
				<select name="year" class="form-control" >
					<c:forEach items="${yearReports}" var="report">
						<option value="${report.year}" 
							<c:if test="${report.id eq currentYearReport.id}" >selected</c:if>>${report.year}</option>
					</c:forEach>
				</select>
				<br>
				<button type="submit">Set Current Year Report</button>
		</form:form>
		
		
		<form:form modelAttribute="yearReport" method="GET"
			action="${contextRoot}/admin/newcurrentyearreport">		
				
				<label for="year">Year:</label>
				<input id="year" type="text" name="year" placeholder="YYYY" required/>
				<br>
				<button type="submit">Add Current Year Report</button>
		</form:form>

	</div>
</section>
</main>
<script>
	
</script>
<%@include file="./common/footer.jsp"%>


