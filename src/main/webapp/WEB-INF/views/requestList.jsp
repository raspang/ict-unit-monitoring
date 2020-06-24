<%@include file="./common/header.jsp"%>

<main class="main-section">
<section class="admin">

	<%@include file="./common/admin-side.jsp"%>

	<div class="admin-main">
		<div class="admin-main-header">
			<h1>List of Requests</h1>
		</div>
		<a href="${contextRoot}/admin/newrequest"
			class="admin-form-button-new">Add entry</a>
	
			<table id="requestList" class="dataTable">
				<thead>
					<tr>
						<th>Request ID</th>
						<th>Unit Name</th>
						<th>Unit Model No.</th>
						<th>Date</th>
						<th>Requested By</th>
						<th>Description Of Malfunction</th>
						<th></th>
						<th></th>
					</tr>
				</thead>

			</table>
		

	</div>
</section>
</main>
<script>
	document.getElementById("requestId").classList.add("admin-side-current");
</script>
<%@include file="./common/footer.jsp"%>


