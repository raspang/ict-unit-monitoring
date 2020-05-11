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


</head>
<body>
	<header class="main-header">
		<nav>
			<h1 id="logo">Unit Monitoring</h1>
			<ul>
				<li><a href="${contextRoot}/admin/">LOGIN</a></li>
			</ul>

		</nav>
	</header>