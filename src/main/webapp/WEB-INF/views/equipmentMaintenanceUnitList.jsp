<%@include file="./common/header.jsp"%>
<script>
	window.unitId = '${unit.id}';
</script>
<main class="main-section">
<section class="admin">

	<%@include file="./common/admin-side.jsp"%>

	<div class="admin-main">
		<c:if test="${unit ne null }">
		<div class="admin-main-header">
			<h1>Unit: ${unit.equipmentName }</h1>
			<p>Model No.: ${unit.modelNo}</p>
			<p>Serial No.: ${unit.serialNo}</p>
			<p>Other Component(s): ${unit.otherComponent}</p>
		</div>
		</c:if>
		<a href="${contextRoot}/admin/newequipmentmaintenance-unit-${unit.id}" class="admin-form-button-new">Add
			entry</a>
		<table id="equipmentMaintenanceUnitList" class="dataTable">
			<thead>
				<tr>
					<th>Request ID</th>
					<th>Request Date</th>
					<th>Operation Performed</th>
					<th>Date Performed</th>
					<th>Performed By</th>
					<th>Remarks</th>
					<th></th>
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


