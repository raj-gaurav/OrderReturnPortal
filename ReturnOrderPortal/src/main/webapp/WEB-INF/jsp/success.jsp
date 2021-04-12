<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
					<li class="nav-item active"><a href="/logout"
						class="btn btn-danger"> Log out</a></li>
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
					<div class="card-body">
						<h4 class="card-title">
							Processed Successfully <span class="material-icons text-success">
								verified </span>
						</h4>
						<p class="card-text">
						<div class="alert alert-success">
							Dear <b>${user}</b>,<br> Your payment of <b>Rs.
								${paymentRequest.processingCharge }</b> was successful.<br> <br>
							<b>Request ID :</b> ${paymentRequest.requestId }<br>
						</div>

						<br>
						<hr />

						<h4>Return Order Details</h4>
						<hr>
						<div class="row">
							<div class="col-sm-12 col-lg-6">
								<label><b>UserID:</b> ${request.userId}</label>
							</div>
							<div class="col-sm-12 col-lg-6">
								<label><b>Contact No. :</b> ${request.contactNumber }</label>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<label><b>Component Type :</b> ${request.componentType}</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 col-lg-6">
								<label><b>Component Name :</b> ${request.componentName}</label>
							</div>
							<div class="col-sm-12 col-lg-6">
								<label><b>Quantity :</b> ${request.quantity }</label>
							</div>
						</div>

						<div class="row">
							<div class="col">
								<label><b>Details :</b> ${request.details } </label>
							</div>
						</div>
						<div class="row alert alert-success">
							<div class="col-sm-12 col-lg-6">
								<label><b>Total Charge : </b> ${processresponse.total} </label>
							</div>
							<div class="col-sm-12 col-lg-6">
								<label><b>Estm. DOD : </b> ${processresponse.dateOfDelivery} </label>
							</div>
						</div>

						<br>
						<hr />
						<br>
						<h6 class="small">
							Thank you for trusting us. For any queries contact +91
							8587XXXX04.
							<h6></h6>
							</p>
					</div>


				</div>

			</div>
		</div>
	</div>






</body>
</html>