<%@include file="./common/header.jsp"%>

<main class="main-section">
<section class="admin">

	<%@include file="./common/admin-side.jsp"%>

	<div class="admin-main">
		<div class="admin-main-header">
			<h1>List of Preventive Maintenance</h1>
		</div>
		<a href="${contextRoot}/admin/newpreventivemaintenance" class="admin-form-button-new">Add entry</a>
		<table id="preventiveMaintenanceList" class="dataTable">
			<thead>
				<tr>
					<th>Serial No</th>
					<th>Unit</th>
					<th>Model</th>					
					<th>Prepared By</th>
					<th>Prepared Date</th>
					<th></th>	
					<th></th>
				</tr>
			</thead>
		</table>

	</div>
</section>
</main>
<script>
document.getElementById("preventiveId").classList.add("admin-side-current");
</script>
<%@include file="./common/footer.jsp"%>


