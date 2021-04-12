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
			<div class="col-sm-12 col-lg-6 ">
				<div class="card m-2 p-3">
					<h2>Confirm your Return Order</h2>
					<h6 class="mt-2 mb-3">The Process details for you return order are:</h6>
					<form action="/payment" method="post">
						<div class="form-group">
							<label for="OrderRequestID"><b>Order Request ID :</b> ${response.requestId}  </label> 
						</div>
						<div class="form-group">
							<label for="UserID"><b>User ID :</b> ${response.userId}  </label> 
						</div>
						<div class="form-group">
							<label for="packaging&Delivery"><b>Packaging & Delivery Charge : </b> Rs. ${response.packagingAndDeliveryCharge}  </label> 
						</div>
						<div class="form-group">
							<label for="processingCharge"><b>Processing Charge :</b> Rs. ${response.processingCharge} </label> 
						</div>
						<div class="form-group">
							<label for="totalCharge"><b>Total Charge:</b> Rs. ${response.total}  </label> 
						</div>
						<div class="form-group">
							<label for="estDate"><b>Estimated Date of Delivery :</b> ${response.dateOfDelivery }  </label> 
						</div>
						<button class="btn btn-primary">Proceed for Payment</button>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>