<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="./common/header.jsp"%>

<main class="main-section">
<section class="admin">

	<%@include file="./common/admin-side.jsp"%>

	<c:choose>
		<c:when test="${edit}">
			<c:set var="action" value="${contextRoot}/admin/edit-user-${user.id}" />
		</c:when>
		<c:otherwise>
			<c:set var="action" value="${contextRoot}/admin/newuser" />
		</c:otherwise>
	</c:choose>


	<div class="admin-main">

		<div class="form-admin-wrapper">
			<header class="form-admin-head">
				<h2>Employee Registration Form</h2>
			</header>

			<form:form modelAttribute="user" method="POST" action="${action}">
				<form:input type="hidden" path="id" />
				<div class="form-admin-input">
					<div class="admin-form">
						<label for="ssoId">Username:</label>
						<form:input id="ssoId" type="text" path="ssoId" required="required"/>
						<div class="has-error">
							<form:errors path="ssoId" class="help-inline" />
						</div>
					</div>

					<div class="admin-form">
						<label for="password">Password:</label>
						<form:input id="password" type="password" path="password" />
					</div>
					<div class="admin-form">
						<label for="password">Role:</label>
						<form:select path="userProfiles" items="${roles}" multiple="false"
							itemValue="id" itemLabel="type" class=""></form:select>
					</div>

				</div>
				<div class="form-admin-input">

					<div class="admin-form">
						<label for="firstName">First name:</label>
						<form:input id="firstName" type="text" path="firstName" required="required" />
					</div>

					<div class="admin-form">
						<label for="middleName">Middle Name:</label>
						<form:input id="middleName" type="text" path="middleName" />
					</div>

					<div class="admin-form">
						<label for="lastName">Last Name:</label>
						<form:input id="lastName" type="text" path="lastName" required="required"/>
					</div>

					<div class="admin-form">
						<label for="birthDate">Birth Date:</label>
						<form:input id="birthDate" type="date" path="birthDate" />
					</div>

					<div class="admin-form">
						<label for="gender">Gender:</label>
						<form:radiobutton class="radio-form" id="gender" path="gender"
							value="Male" checked="checked" />
						Male
						<form:radiobutton class="radio-form"  id="gender" path="gender" value="Famale" />
						Famale
					</div>

					<div class="admin-form">
						<label for="position">Position:</label>
						<form:input id="position" type="text" path="position" />
					</div>

					<div class="admin-form">
						<label for="division">Division:</label>
						<form:input id="division" type="text" path="division" />
					</div>

					<div class="admin-form">
						<label for="email">Email:</label>
						<form:input id="email" type="email" path="email" />
					</div>

					<div class="admin-form">
						<label for="contact">Contact No.:</label>
						<form:input id="contact" type="text" path="contact" />
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
				<a href="<c:url value='/admin/user' />"
					class="form-admin-button-cancel">Cancel</a>

				<%-- <c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list' />">Cancel</a>
						</c:otherwise>
					</c:choose>
					 --%>



			</form:form>
		</div>

	</div>
</section>
</main>
<script>
document.getElementById("employeesId").classList.add("admin-side-current");
</script>
<%@include file="./common/footer.jsp"%>


