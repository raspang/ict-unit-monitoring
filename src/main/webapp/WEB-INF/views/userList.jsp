<%@include file="./common/header.jsp"%>

<main class="main-section">
<section class="admin">


	<%@include file="./common/admin-side.jsp"%>

	<div class="admin-main">
			<div class="admin-main-header">
			<h1>List of Employees</h1>
		</div>
		<a href="${contextRoot}/admin/newuser" class="admin-form-button-new">Add entry</a>
		<table id="userList">		
			<thead>
				<tr>
					<th>Firstname</th>
					<th>Lastname</th>
					<th>Username</th>
					<th>Position</th>
					<th>Division</th>
					<sec:authorize access="hasRole('ADMIN')">
					<th></th>
					</sec:authorize>
				</tr>
			</thead>

		</table>

	</div>
</section>
</main>
    <script>
      document.getElementById("employeesId").classList.add("admin-side-current");
    </script>
<%@include file="./common/footer.jsp"%>


