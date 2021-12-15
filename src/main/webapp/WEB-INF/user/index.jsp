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
<title>Starting page</title>
</head>
<body>

	<div class="container">
	<div class="text-center">
		<h1>Project Manager</h1>
		<p>A palce for teams to manage projects.</p>
	</div>
		<div class="d-flex">

			<!-- form for log in -->

			<div class="col p-5">
				<h1>Register</h1>
				<form:form action="/register" method="post" modelAttribute="newUser">
					<form:errors path="*" class="text-danger" />
					<div class="form-group">
						<label>First Name: </label>
						<form:input path="firstName" class="form-control" />
					</div>
					<div class="form-group">
						<label>Last Name: </label>
						<form:input path="lastName" class="form-control" />
					</div>
					<div class="form-group">
						<label>Email:</label>
						<form:input path="email" class="form-control" />
					</div>
					<div class="form-group">
						<label>Password:</label>
						<form:password path="password" class="form-control" />
					</div>
					<div class="form-group">
						<label>Confirm Password:</label>
						<form:password path="confirmPassword" class="form-control" />
					</div>
					<input type="submit" value="Register" class="btn btn-primary" />
				</form:form>
			</div>

			<!-- form for log in -->

			<div class="col p-5">
				<h1>Log in</h1>
				<form:form action="/login" method="post" modelAttribute="newLogin">
					<div class="form-group">
						<label>Email:</label>
						<form:input path="email" class="form-control" />
						<form:errors path="email" class="text-danger" />
					</div>
					<div class="form-group">
						<label>Password:</label>
						<form:password path="password" class="form-control" />
						<form:errors path="password" class="text-danger" />
					</div>
					<input type="submit" value="Login" class="btn btn-success" />
				</form:form>
			</div>
		</div>

	</div>

</body>
</html>