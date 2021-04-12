<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Return Order Portal</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #D3D3D3;">


	<!-- Navbar Start-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Return Order Portal </a>
			</div>

		</div>
	</nav>
	<!-- Navbar End-->

	<div class="container">
		<div class="row">
			<div class="col-sm-12 col-lg-3"></div>
			<div class="col-sm-12 col-lg-6">

				<div class="card p-3 m-2">
					<h2 style="text-align:center">Login</h2>
					<form:form action="/login" method="post">
						<div class="form-group">

							<c:choose>
								<c:when test="${error}">
									<div class="alert alert-danger" role="alert">
										<strong>Invalid Credentials !!! </strong> Please try again.
									</div>
								</c:when>
								<c:otherwise>

								</c:otherwise>
							</c:choose>



						</div>
						<div class="form-group">
							<label for="username">Username : </label> <input type="text"
								class="form-control" name="user" required="required" />
						</div>
						<div class="form-group">
							<label for="password">Password : </label> <input type="password"
								class="form-control" name="pass" required="required" />
						</div>
						<button class="btn btn-primary">Log In</button>
					</form:form>
				</div>
			</div>
		</div>

	</div>

</body>
</html>