<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="./common/header.jsp"%>

<main class="main-section">
<section class="admin">

	<%@include file="./common/admin-side.jsp"%>

	<c:choose>
		<c:when test="${edit}">
			<c:set var="action"
				value="${contextRoot}/admin/edit-request-${request.id}" />
		</c:when>
		<c:otherwise>
			<c:set var="action" value="${contextRoot}/admin/newrequest" />
		</c:otherwise>
	</c:choose>


	<div class="admin-main">

		<div class="form-admin-wrapper">
			<header class="form-admin-head">
				<h2>Request For Repair I.T. Equipment Form</h2>
			</header>

			<form:form modelAttribute="request" method="POST" action="${action}">
				<form:input type="hidden" path="id" />
				<div class="form-admin-input">
					<div class="admin-form">
						<label for="unit">Unit:</label>
						<form:select path="unit" items="${units}" multiple="false"
							itemValue="id" itemLabel="equipmentName" class=""></form:select>
					</div>

					<div class="admin-form">
						<label for="date">Date Request:</label>
						<form:input id="date" type="date" path="date" />
					</div>

					<div class="admin-form">
						<label for="descriptionOfMalfunction">Description Of
							Malfunction:</label>
						<form:input id="descriptionOfMalfunction" type="text"
							path="descriptionOfMalfunction" />
					</div>

					<div class="admin-form">
						<label for="requestBy">Request By:</label>
						<form:select path="requestBy" items="${users}" multiple="false"
							itemValue="id" itemLabel="fullName" class=""></form:select>
					</div>
				</div>
				<div class="form-admin-input">
					<div class="admin-form">
						<label for="recommendation">Recommendation:</label>
						<form:input id="recommendation" type="text" path="recommendation" />
					</div>

					<div class="admin-form">
						<label for="recommendedBy">Recommended By:</label>
						<form:select path="recommendedBy" items="${users}"
							multiple="false" itemValue="id" itemLabel="fullName" class=""></form:select>
					</div>

					<div class="admin-form">
						<label for="inspectedBy">Inspected By:</label>
						<form:select path="inspectedBy" items="${users}" multiple="false"
							itemValue="id" itemLabel="fullName" class=""></form:select>
					</div>

					<div class="admin-form">
						<label for="notedBy">Noted By:</label>
						<form:select path="notedBy" items="${users}" multiple="false"
							itemValue="id" itemLabel="fullName" class=""></form:select>
					</div>
				</div>
				<div class="form-admin-input">
					<div class="admin-form">
						<label for="inHouseRepairedBy">In-House Repaired By:</label>
						<form:select path="inHouseRepairedBy" items="${users}"
							multiple="false" itemValue="id" itemLabel="fullName" class=""></form:select>
					</div>

					<div class="admin-form">
						<label for="inHouseReceivedBy">In-House Received By:</label>
						<form:select path="inHouseReceivedBy" items="${users}"
							multiple="false" itemValue="id" itemLabel="fullName" class=""></form:select>
					</div>

					<div class="admin-form">
						<label for="isApproved">Status:</label>
						<form:radiobutton class="radio-form" id="isApproved"
							path="isApproved" value="false" checked="checked" />
						Not Yet Approved
						<form:radiobutton class="radio-form" id="isApproved" path="isApproved" value="true" />
						Approved
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
				<a href="<c:url value='/admin/request' />"
					class="form-admin-button-cancel">Cancel</a>
			</form:form>
		</div>

	</div>
</section>
</main>
<script>
document.getElementById("requestId").classList.add("admin-side-current");
</script>
<%@include file="./common/footer.jsp"%>


