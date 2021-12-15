<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- Bootstrap JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<title>Starting page</title>
</head>
<body>

	<div class="container">
		<!-- SHOW DATA WITH EDIT ROUTE / DELETE FORM-->
		<div class="d-flex justify-content-between">
			<h1>Project Details</h1>
			<p><a href="/dashboard">Back to Dashboard</a></p>
		</div>
		
		<h5>Project: <c:out value="${ project.title }"/></h5>
		<h5>Description: <c:out value="${ project.description }"/></h5>		
		<h5>Due Date: <fmt:formatDate pattern="MM/dd/yyyy" value="${ project.dueDate }"/></h5>
		
		<a href="#">See tasks!</a>
	</div>

</body>
</html>