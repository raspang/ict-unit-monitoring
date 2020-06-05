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
				<div class="form-wrapper-admin-board">
					<label for="currentYear">Select Current Year:</label> 
					<select
						id="currentYear" name="year" class="form-control">
						<c:forEach items="${yearReports}" var="report">
							<option value="${report.year}"
								<c:if test="${report.id eq currentYearReport.id}" >selected</c:if>>${report.year}</option>
						</c:forEach>
					</select>
					<button type="submit">Save Selected Year Report</button>
				</div>
			</form:form>


			<form:form modelAttribute="yearReport" method="GET"
				action="${contextRoot}/admin/newcurrentyearreport">
				<div class="form-wrapper-admin-board">
					<label for="year">Year:</label>
					<input id="year" type="text" name="year" placeholder="YYYY" required />
					<button type="submit">Add Current Year Report</button>
				</div>
			</form:form>
		
		</div>
	</section>
</main>
<script>
	document.getElementById("yearreportId").classList.add("admin-side-current");
</script>
<%@include file="./common/footer.jsp"%>


