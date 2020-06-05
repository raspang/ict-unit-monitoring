<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="./common/header.jsp"%>

<main class="main-section">
	<section class="admin">

		<%@include file="./common/admin-side.jsp"%>

		<c:choose>
			<c:when test="${edit}">
				<c:set var="action"
					value="${contextRoot}/admin/edit-preventivemaintenance1-${preventiveMaintenance.id}" />
			</c:when>
			<c:otherwise>
				<c:set var="action"
					value="${contextRoot}/admin/newpreventivemaintenance" />
			</c:otherwise>
		</c:choose>


		<div class="admin-main">

			<div class="form-admin-wrapper">
				<header class="form-admin-head">
					<h2>Preventive Maintenance Form</h2>
				</header>

				<form:form modelAttribute="preventiveMaintenance" method="POST"
					action="${action}">
					<form:input type="hidden" path="id" />
					<div class="form-admin-input">
						<div class="admin-form">
							<label for="unit">Unit:</label>
							<form:select path="unit" items="${units}" multiple="false"
								itemValue="id" itemLabel="displayUnit" class=""></form:select>
						</div>

						<div class="admin-form">
							<label for="preparedBy">Prepared By:</label>
							<form:select path="preparedBy" items="${users}" multiple="false"
								itemValue="id" itemLabel="fullName" class=""></form:select>
						</div>

						<div class="admin-form">
							<label for="approvedBy">Approved By:</label>
							<form:select path="approvedBy" items="${users}" multiple="false"
								itemValue="id" itemLabel="fullName" class=""></form:select>
						</div>

						<div class="admin-form">
							<label for="preparedDate">Prepared Date:</label>
							<form:input id="preparedDate" type="date" path="preparedDate" />
						</div>

						<div class="admin-form">
							<label for="approvedBy">Approved Date:</label>
							<form:input id="approvedBy" type="date" path="approvedBy" />
						</div>



					</div>

					<div class="form-admin-input">
						<div class="admin-form">
							<label for="completed"><i>Mark as :</i></label>
							<form:radiobutton class="radio-form" id="completed"
								path="completed" value="false" checked="checked" />
							Not Yet Completed
							<form:radiobutton class="radio-form" id="completed"
								path="completed" value="true" />
							Completed
						</div>
					</div>
					<hr/>
					<div class="form-admin-input-check">
						<label for="checkAll"><b>One Time</b> Maintenance
							Check-Up:</label>
						<div class="form-admin-input-check">
							<form:checkboxes items="${months}" path="checkAll" />
						</div>
					</div>
					<hr />

					<div class="form-admin-input-check">
						<label for="check1">Clean windows registry :</label>
						<div class="form-admin-input-check">
							<form:checkboxes items="${months}" path="check1" />
						</div>
					</div>

					<div class="form-admin-input-check">
						<label for="check2">Delete Temporary Internet Files:</label>
						<div class="form-admin-input-check">
							<form:checkboxes items="${months}" path="check2" />
						</div>
					</div>

					<div class="form-admin-input-check">
						<label for="check3">Disk Cleanup:</label>

						<div class="form-admin-input-check">
							<form:checkboxes items="${months}" path="check3" />
						</div>
					</div>

					<div class="form-admin-input-check">
						<label for="check4">Scandisk:</label>
						<div class="form-admin-input-check">
							<form:checkboxes items="${months}" path="check4" />
						</div>
					</div>

					<div class="form-admin-input-check">
						<label for="check5">Disk Cleanup:</label>
						<div class="form-admin-input-check">
							<form:checkboxes items="${months}" path="check5" />
						</div>
					</div>

					<div class="form-admin-input-check">
						<label for="check6">Scandisk:</label>
						<div class="form-admin-input-check">
							<form:checkboxes items="${months}" path="check6" />
						</div>
					</div>

					<div class="form-admin-input-check">
						<label for="check7">Disk Defragmenter:</label>
						<div class="form-admin-input-check">
							<form:checkboxes items="${months}" path="check7" />
						</div>
					</div>

					<div class="form-admin-input-check">
						<label for="check8">Uninstall Unnecessary Programs:</label>
						<div class="form-admin-input-check">
							<form:checkboxes items="${months}" path="check8" />
						</div>
					</div>

					<div class="form-admin-input-check">
						<label for="check9">Clean inside the system unit and
							peripherals:</label>
						<div class="form-admin-input-check">
							<form:checkboxes items="${months}" path="check9" />
						</div>
					</div>

					<div class="form-admin-input-check">
						<label for="check10">Reseat all socketed chips on the
							system board and peripheral cards:</label>
						<div class="form-admin-input-check">
							<form:checkboxes items="${months}" path="check10" />
						</div>
					</div>


					<div class="form-admin-input-check">
						<label for="check11">Check CPU and Power Supply Fans
							connectors and proper operation:</label>
						<div class="form-admin-input-check">
							<form:checkboxes items="${months}" path="check11" />
						</div>
					</div>


					<div class="form-admin-input-check">
						<label for="check12">Check CMOS Battery:</label>
						<div class="form-admin-input-check">
							<form:checkboxes items="${months}" path="check12" />
						</div>
					</div>


					<div class="form-admin-input-check">
						<label for="check13">Check cables and connectors
							conditions (Input and output Ports):</label>
						<div class="form-admin-input-check">
							<form:checkboxes items="${months}" path="check13" />
						</div>
					</div>

					<div class="form-admin-input-check">
						<label for="check14">Check AVR and UPS Voltage Output:</label>
						<div class="form-admin-input-check">
							<form:checkboxes items="${months}" path="check14" />
						</div>
					</div>
					<hr>

					<c:choose>
						<c:when test="${edit}">
							<button type="submit">Update</button>
						</c:when>
						<c:otherwise>
							<button type="submit">Register</button>
						</c:otherwise>
					</c:choose>

					<a href="<c:url value='/admin/preventive' />"
						class="form-admin-button-cancel">Cancel</a>

				</form:form>
			</div>

		</div>
	</section>
</main>
<script>
	document.getElementById("preventiveId").classList.add("admin-side-current");
</script>
<%@include file="./common/footer.jsp"%>


