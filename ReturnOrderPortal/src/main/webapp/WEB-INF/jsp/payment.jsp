<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
			<button class="navbar-toggler" data-toggle="collapse"
				data-target="#navbaritems">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbaritems">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a href="/logout" class="btn btn-danger">
							Log out</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Navbar End-->

	<div class="container">
		<div class="row">
			<div class="col-sm-12 col-lg-3"></div>
			<div class="col-sm-12 col-lg-6">
				<div class="card m-2 p-3">
					<h4 class="mb-4">Enter Details for Payment :</h4>
					<hr />

					<form:form action="/validateCard" method="post"
						modelAttribute="paymentPage">
						<div class="row">
							<div class="col">
								<div class="form-group">
									<c:choose>
										<c:when test="${valid}">
											<div class="alert alert-danger" role="alert">
												<strong>Invalid Credit Card Number !!! </strong> Please try
												again.
												<form:label path="creditCardNumber">Credit Card No. : </form:label>
												<form:input class="form-control" placeholder="Enter valid Credit Card Number" path="creditCardNumber"
													 required="required"/>
												<form:errors class="cssError small" path="creditCardNumber" />
											</div>
										</c:when>
										<c:otherwise>
											<form:label path="creditCardNumber">Credit Card No. : </form:label>
											<form:input class="form-control" path="creditCardNumber"
												value="${card}" required="required" readonly="true"/>
										</c:otherwise>
									</c:choose>
								</div>

							</div>
						</div>
						<div class="row">
							<div class="col">
								<div class="form-group">
									<form:label path="requestId">Request Id : </form:label>
									<form:input class="form-control" path="requestId"
										value="${processresponse.requestId}" readonly="true"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<div class="form-group">
									<form:label path="processingCharge">Total Processing Charge : </form:label>
									<form:input class="form-control" path="processingCharge"
										value="${processresponse.total}" readonly="true"/>
								</div>
							</div>

						</div>
						<div class="row">
							<div class="col">
								<button class="btn btn-primary">Confirm Payment</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>