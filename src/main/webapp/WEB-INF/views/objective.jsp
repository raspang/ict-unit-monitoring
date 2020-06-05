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
						<select name="month" onchange="this.form.submit()">
							<option value="">Select</option>
							<option value="January">January</option>
							<option value="February">February</option>
							<option value="March">March</option>
							<option value="April">April</option>
							<option value="May">May</option>
							<option value="June">June</option>
							<option value="July">July</option>
							<option value="August">August</option>
							<option value="September">September</option>
							<option value="October">October</option>
							<option value="November">November</option>
							<option value="December">December</option>
						</select>
					</form:form>
				</p>

			</div>

			<div class="form-wrapper-admin-board">
				<label>No. of Computer Unit Available</label>
				<label>No. of Preventive Management Performed</label>
				<label>No. of Repair Performed</label>
				<label>Cause of Repair</label>
				<label>Description of Work Done</label>
				<label>Date of Repair</label>
				<label>REMARKS</label>
			</div>
		</div>
	</section>
</main>
<script>
	document.getElementById("objectiveId").classList.add("admin-side-current");
</script>
<%@include file="./common/footer.jsp"%>


