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
		<div class="d-flex justify-content-between">
			<h1>Welcome, ${ user.firstName }!</h1>
			<a href="/logout">log out</a>
		</div>
		<div class="d-flex justify-content-between">
			<h5>All Projects</h5>
			<a class="btn btn-primary" href="projects/new">New Project</a>
		</div>
		<!-- SHOW DATA WITH EDIT ROUTE / DELETE FORM-->
		<div class="row">
			<table class="table">
				<thead>
					<tr>
						<th>Project</th>
						<th>Team Lead</th>
						<th>Due Date</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="project" items="${ projects }">
						<tr>
							<c:if test="${ project.creatorId.id != user_id && project.teamUsers.contains(user) == false  }">
								<td><a href="/projects/${ project.id }"> <c:out
											value="${ project.title }" />
								</a></td>
								<td><c:out value="${ project.creatorId.firstName }" /></td>
								<td><fmt:formatDate pattern="MMM dd" value="${ project.dueDate }"/></td>
								<td><a class="btn" href="/project/${ project.id }/join">Join
										team</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<h5>Your Projects</h5>
			<table class="table">
				<thead>
					<tr>
						<th>Project</th>
						<th>Team Lead</th>
						<th>Due Date</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="project" items="${ projects }">
						<c:if test="${ project.creatorId.id == user_id }">
							<tr>
								<td><a href="/projects/${ project.id }"> <c:out
											value="${ project.title }" />
								</a></td>
								<td><c:out value="${ project.creatorId.firstName }" /></td>
								<td><fmt:formatDate pattern="MMM dd" value="${ project.dueDate }"/></td>
								<td><a class="btn" href="/project/${ project.id }/edit">Edit</a></td>
							</tr>
						</c:if>

						<c:if test="${ project.teamUsers.contains(user) }">
							<tr>

								<td><a href="/projects/${ project.id }"> <c:out
											value="${ project.title }" />
								</a></td>
								<td><c:out value="${ project.creatorId.firstName }" /></td>
								<td><fmt:formatDate pattern="MMM dd" value="${ project.dueDate }"/></td>
								<td><a class="btn" href="/project/${ project.id }/leave">Leave
										team</a></td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>