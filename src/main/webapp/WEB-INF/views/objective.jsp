<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="./common/header.jsp"%>

<main class="main-section">
<section class="admin">

	<%@include file="./common/admin-side.jsp"%>

	<div class="admin-main">

		<div class="admin-main-header">
			<h1>Monitoring Report of Preventive Maintenance Functional
				Objective</h1>

			<p style="display: inline">
				For the Month of
				<form:form action="${contextRoot}/admin/objective" method="GET"
					style="display:inline">

					<select name="month" id="monthX" onchange="this.form.submit()">
						<option value="">Select</option>
						<option value="1">January</option>
						<option value="2">February</option>
						<option value="3">March</option>
						<option value="4">April</option>
						<option value="5">May</option>
						<option value="6">June</option>
						<option value="7">July</option>
						<option value="8">August</option>
						<option value="9">September</option>
						<option value="10">October</option>
						<option value="11">November</option>
						<option value="12">December</option>
					</select>
				</form:form>
				
			</p>

		</div>



		<ul style="list-style-type: none;text-align: left;margin-left:3rem">
			<li>No. of Computer Unit Available: ${noUnits }</li>
			<li>No. of Preventive Management Performed:
				${noPreventiveManagementPerformed }</li>
			<li>No. of Repaired Performed: ${noOfRepairPerformed }</li>
			<c:if test="${monthX ne null }">
			<li><a href="${contextRoot}/admin/objective/pdf?month=${monthX}" class="float:left"><img
					src="${contextRoot}/static/icons/icons8-export-pdf-48.png"
					alt="edit" width="25px" height="24px" /></a></li>
			</c:if>
		</ul>




		<table>
			<tr>
				<td><b>Cause of Repair</b></td>
				<td><b>Description of Work Done</b></td>
				<td><b>Date of Repaired</b></td>
				<td><b>REMARKS</b></td>
			</tr>

			<c:forEach items="${repairDetails}" var="item">
				<tr>
					<td>${item.request.descriptionOfMalfunction}</td>
					<td>${item.operationPerformed}</td>
					<td>${item.date}</td>
					<td>${item.remark}</td>
				</tr>
			</c:forEach>
		</table>

	</div>
</section>
</main>
<script>
	document.getElementById("objectiveId").classList.add("admin-side-current");
	
	 let element = document.getElementById("monthX");
	 element.value = monthX;
	    
	    
</script>
<%@include file="./common/footer.jsp"%>


