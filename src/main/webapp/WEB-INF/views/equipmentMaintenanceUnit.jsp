<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="./common/header.jsp"%>

<main class="main-section">
<section class="admin">

	<%@include file="./common/admin-side.jsp"%>

	<c:choose>
		<c:when test="${edit}">                             
			<c:set var="action" value="${contextRoot}/admin/editequipmentmaintenance-unit" />
		</c:when>
		<c:otherwise>
			<c:set var="action" value="${contextRoot}/admin/newequipmentmaintenance-unit" />
		</c:otherwise>
	</c:choose>


	<div class="admin-main">

		<div class="form-admin-wrapper">
			<header class="form-admin-head">
				<h2>Equipment Maintenance Form</h2>
			</header>

			<form:form modelAttribute="equipmentMaintenance" method="POST" action="${action}">
				<form:input type="hidden" path="id" />

				<div class="form-admin-input">

					<div class="admin-form">
						<label for="receivedBy">Request ID:</label>
						<form:select path="request" items="${requests}" multiple="false"
							itemValue="id" itemLabel="id" class=""></form:select>
					</div>

					<div class="admin-form">
						<label for="operationPerformed">Operation Performed:</label>
						<form:input id="operationPerformed" type="text" path="operationPerformed" required="required"/>
					</div>
					
					<div class="admin-form">
						<label for="date">Date Performed:</label>
						<form:input id="date" type="date" path="date" />
					</div>
					
					<div class="admin-form">
						<label for="receivedBy">Performed By:</label>
						<form:select path="performedBy" items="${users}" multiple="false"
							itemValue="id" itemLabel="fullName" class=""></form:select>
					</div>

					<div class="admin-form">
						<label for="remark">Remark:</label>
						<form:input id="remark" type="text" path="remark" />
					</div>

				</div>
				<c:choose>
					<c:when test="${edit}">
						<button type="submit">Update</button>

					</c:when>
					<c:otherwise>
						<button type="submit">Register</button>
					</c:otherwise>
				</c:choose>
				<a href="<c:url value='/admin/maintenance' />"
					class="form-admin-button-cancel">Cancel</a>

			</form:form>
		</div>

	</div>
</section>
</main>
<script>
document.getElementById("equipmentId").classList.add("admin-side-current");
</script>
<%@include file="./common/footer.jsp"%>


