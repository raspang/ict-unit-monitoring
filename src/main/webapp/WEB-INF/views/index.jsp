
<%@include file="./common/header.jsp"%>

<main>
<section class="hero">
	<div class="hero-intro">
		<img src="<c:url value='/static/images/dost.png' />" alt="DOST">
		<h2>DOST Region No. XII</h2>
		

	
	</div>
	<sec:authorize access="hasRole('EMPLOYEE')">
				<h3>Request For Repair I.T. Equipment:</h3><a href="${contextRoot}/employee/newrequest" style="width:42px;height:42px;"><img
					src="${contextRoot }/static/icons/icons8-view-64.png" alt="edit"
					/></a>
	</sec:authorize>
	<sec:authorize access="!hasRole('EMPLOYEE')">
	<div class="hero-details">
		<div class="hero-details-img">
			<img src="<c:url value='/static/images/laptop.png' />" alt="">
		</div>
		<div class="hero-details-service">
			<h3>ICT Unit Monitoring</h3>
			<ul>
				<li>Request For Repair I.T. Equipment:</li>
				<li>Equipment Maintenance Record</li>
				<li>Preventive Maintenance Schedule</li>
				<li>Preventive Maintenance Functional Objective</li>
			</ul>
		</div>
	</div>
	</sec:authorize>
</section>
</main>

{ }
<%@include file="./common/footer.jsp"%>
