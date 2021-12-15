<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>

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

		<h1>Edit Project</h1>

		<!-- EDIT CLASS FORM -->
		<form:form action="/project/${project.id}/edit" method="post"
			modelAttribute="project">
			<input type="hidden" name="_method" value="put">
			<form:errors path="*" />
			<form:input type="hidden" path="creatorId" value="${ user_id }" />
			
			<p>
				<form:label path="title">Project Title:</form:label>
				<form:input path="title" />
			</p>
			<p>
				<form:label path="description">Project Description:</form:label>
				<form:input path="description" />
			</p>
			<p>
				<form:label path="dueDate">Due Date:</form:label>
				<form:input type="date" path="dueDate" />
			</p>
			<a href="/dashboard">Cancel</a>
			<input type="submit" value="Submit" />
		</form:form>


	</div>

</body>
</html>