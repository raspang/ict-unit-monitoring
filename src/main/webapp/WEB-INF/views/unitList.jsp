<%@include file="./common/header.jsp"%>

<main class="main-section">
<section class="admin">

	<%@include file="./common/admin-side.jsp"%>

	<div class="admin-main">
		<div class="admin-main-header">
			<h1>List of Unit Profiles</h1>
		</div>
		<a href="${contextRoot}/admin/newunit" class="admin-form-button-new">Add entry</a>
		<table id="unitList" class="dataTable">
			<thead>
				<tr>
					<th>Equipment Name</th>
					<th>Model No</th>
					<th>Serial No</th>
					<th>Other Component</th>			
					<th>Received By</th>		
					<th></th>
				</tr>
			</thead>
		</table>

	</div>
</section>
</main>
<script>
document.getElementById("unitsId").classList.add("admin-side-current");
</script>
<%@include file="./common/footer.jsp"%>


