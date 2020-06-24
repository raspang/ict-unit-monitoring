<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<meta name="description"
	content="ICT Department of DOST Region no. XII - Monitoring Unit System" />
<meta name="robots" content="index,follow" />

<link rel="stylesheet"
	href="<c:url value='/static/css/dataTables.bootstrap.css' />" />
<link rel="stylesheet"
	href="<c:url value='/static/css/datatables.css' />" />
<link rel="stylesheet" href="<c:url value='/static/style.css' />" />

<title>ICT | Unit Monitoring System</title>
<script>
	window.contextRoot = '${contextRoot}'
	window.userRole = '';
</script>

<sec:authorize access="hasRole('ADMIN')">
	<script>
		window.userRole = 'ADMIN';
	</script>
</sec:authorize>
<sec:authorize access="hasRole('EMPLOYEE')">
	<script>
		window.userRole = 'EMPLOYEE';
	</script>
</sec:authorize>

</head>
<body>
	<header class="main-header">
		<nav>


			<sec:authorize access="isAnonymous()">
				<h1 id="logo">Unit Monitoring</h1>
				<ul>
					<li><a href="${contextRoot}/login">Login</a></li>
				</ul>
			</sec:authorize>

			<sec:authorize access="isAuthenticated()">
				<h1 id="logo">Unit Monitoring</h1>
				<ul>
					<li><small><code>Hi! ${loggedinuser}, year
								setting:${currentYearReport.year} </code></small>
					<a href="${contextRoot}/logout" style="color: blue">Logout</a></li>

				</ul>
				<c:if test="${currentYearReport eq null}">
					<script>
					alert("Error: Please add Current Year.")
					</script>
				</c:if>
				
			</sec:authorize>


		</nav>
	</header>