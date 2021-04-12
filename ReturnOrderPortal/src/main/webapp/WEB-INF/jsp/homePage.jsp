<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	<script>
		function check(that) {

			if (that.value == "Integral")
				document.getElementById("priorityBox").style.display = "block";
			else
				document.getElementById("priorityBox").style.display = "none";

		}
	</script>
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
					<h2 style="text-align: center">Return Order Details</h2>
					<form:form action="/processDetail" method="post"
						modelAttribute="processRequest">
						<form:hidden path="userId" value="${userId}" />
						<div class="row">
							<div class="col">

								<div class="form-group">
									<form:label path="name">Name : </form:label>
									<form:input path="name" class="form-control"
										required="required" />
									<form:errors class="text-danger small" path="name" />
								</div>
							</div>

						</div>
						<div class="row">
							<div class="col-sm-12 col-lg-6">
								<div class="form-group">
									<form:label path="contactNumber">Contact No. : </form:label>
									<form:input path="contactNumber" class="form-control"
										required="required" />
									<form:errors class="text-danger small" path="contactNumber" />
								</div>
							</div>
							<div class="col-sm-12 col-lg-6">
								<div class="form-group">
									<form:label path="creditCardNumber">Credit Card No. : </form:label>
									<form:input path="creditCardNumber" class="form-control"
										required="required" />
									<form:errors class="text-danger small" path="creditCardNumber" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<div class="form-group">
									<form:label path="componentType">Component Type : </form:label>
									<form:select path="componentType"
										class="browser-default custom-select" id="componentType"
										onchange="check(this)">
										<form:option value="Accessory">Accessory</form:option>
										<form:option value="Integral">Integral</form:option>
									</form:select>
									<form:errors class="text-danger small" path="componentType" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 col-lg-8">
								<div class="form-group">
									<form:label path="componentName" for="componentName">Component Name : </form:label>
									<form:input path="componentName" class="form-control"
										required="required" />
									<form:errors class="text-danger small" path="componentName" />
								</div>
							</div>
							<div class="col-sm-12 col-lg-4">
								<div class="form-group">
									<form:label path="quantity">Quantity : </form:label>
									<form:input class="form-control" path="quantity"
										required="required" type="number"/>
									<form:errors class="text-danger small" path="quantity" />
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col">
								<div class="form-group">
									<form:label path="details">Details of defective
										component : </form:label>
									<form:input path="details" class="form-control"
										required="required" />
									<form:errors class="text-danger small" path="details" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<div class="form-group" id="priorityBox" style="display: none;">
									<form:checkbox path="isPriorityRequest" value="true" />
									<form:label path="isPriorityRequest">isPriorityRequest</form:label>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col">
								<button class="btn btn-primary" type="submit">Confirm
									Return</button>
							</div>
						</div>
					</form:form>
				</div>
				<div class="card m-1 small">
					<div class="container">
						<div class="row mt-1">
							<div class="col-sm-12 col-lg-2">
								<h6 class="small">
									<b>Note :</b>
								</h6>
							</div>
							<div class="col-sm-12 col-lg-10">
								<h6 class="small">
									Component Type <b>Integral</b> will be treated as <b>Return</b>
									and <b>Accessory</b> will be treated as <b>Replace</b>.
								</h6>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

</body>
</html>