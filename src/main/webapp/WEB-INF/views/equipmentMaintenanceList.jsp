<%@include file="./common/header.jsp"%>

<main class="main-section">
<section class="admin">

	<%@include file="./common/admin-side.jsp"%>

	<div class="admin-main">
		<div class="admin-main-header">
			<h1>List of Units</h1>
		</div>

		<table id="equipmentMaintenanceList" class="dataTable">
			<thead>
				<tr>
					<th>Equipment Name</th>
					<th>Model No</th>
					<th>Serial No</th>
					<th>Other Component</th>
					<th>Record Maintenance</th>
				</tr>
			</thead>
		</table>

	</div>
</section>
</main>
<script>
	document.getElementById("equipmentId").classList.add("admin-side-current");
</script>
<%@include file="./common/footer.jsp"%>


