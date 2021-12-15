<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- Bootstrap JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<title>Project Manage</title>
</head>
<body>

	<div class="container">
		<h1>Create a Project</h1>


		<!-- ADD NEW CLASS FORM -->
		<form:form action="/projects/new" method="post"
			modelAttribute="project">
			<%-- 			<form:errors path="*"/>
 --%>
 
 			<form:input type="hidden" path="creatorId" value="${ user_id }"/>
			<p>
				<form:label path="title">Project Title:</form:label>
				<form:errors path="title" />
				<form:input path="title" />
			</p>
			<p>
				<form:label path="description">Project Description:</form:label>
				<form:errors path="description" />
				<form:input path="description" />
			</p>
			<p>
				<form:label path="dueDate">Due Date:</form:label>
				<form:errors path="dueDate" />
				<form:input type="date" path="dueDate" />
			</p>
			<a class="btn btn-primary" href="/dashboard">Cancel</a>
			<input class="btn btn-primary" type="submit" value="Submit" />
		</form:form>

	</div>

</body>
</html>