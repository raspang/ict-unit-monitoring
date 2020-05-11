<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="./common/header.jsp"%>

<main class="main-section">
<section class="admin">

	<%@include file="./common/admin-side.jsp"%>

	<c:choose>
		<c:when test="${edit}">
			<c:set var="action"
				value="${contextRoot}/admin/edit-unit-${unit.id}" />
		</c:when>
		<c:otherwise>
			<c:set var="action" value="${contextRoot}/admin/newunit" />
		</c:otherwise>
	</c:choose>


	<div class="admin-main">

		<div class="form-admin-wrapper">
			<header class="form-admin-head">
				<h2>Unit/PC Form</h2>
			</header>

			<form:form modelAttribute="unit" method="POST" action="${action}">
				<form:input type="hidden" path="id" />
				<div class="form-admin-input">
	

					<div class="admin-form">
						<label for="equipmentName">Equipment Name:</label>
						<form:input id="equipmentName" type="text"
							path="equipmentName" />
					</div>
	
					<div class="admin-form">
						<label for="codeNo">Code No:</label>
						<form:input id="codeNo" type="text"
							path="codeNo" />
					</div>

					<div class="admin-form">
						<label for="modelNo">Model No:</label>
						<form:input id="modelNo" type="text"
							path="modelNo" />
					</div>
														
					<div class="admin-form">
						<label for="serialNo">Serial No:</label>
						<form:input id="serialNo" type="text"
							path="serialNo" />
					</div>
	
						<div class="admin-form">
						<label for=otherComponent>Other Component:</label>
						<form:input id="otherComponent" type="text"
							path="otherComponent" />
					</div>
					
					<div class="admin-form">
						<label for="dateAcquired">Date Acquired:</label>
						<form:input id="dateAcquired" type="date" path="dateAcquired" />
					</div>			    
									
					<div class="admin-form">
						<label for="location">Location Deployed:</label>
						<form:input id="location" type="text"
							path="location" />
					</div>
														

					<div class="admin-form">
						<label for="receivedBy">Received By:</label>
						<form:select path="receivedBy" items="${users}" multiple="false"
							itemValue="id" itemLabel="fullName" class=""></form:select>
					</div>
					
					<div class="admin-form">
						<label for="dateRecieved">Received Date:</label>
						<form:input id="dateRecieved" type="date" path="dateRecieved" />
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
				
				<a href="<c:url value='/admin/unit' />" class="form-admin-button-cancel">Cancel</a>

			</form:form>
		</div>

	</div>
</section>
</main>
<script>
document.getElementById("unitsId").classList.add("admin-side-current");
</script>
<%@include file="./common/footer.jsp"%>


