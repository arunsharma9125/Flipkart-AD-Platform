<!--Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html lang="zxx">

<head>
	<title>Validator Signup Form Responsive Widget Template :: w3layouts</title>
	<!-- Meta tag Keywords -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8" />
	<meta name="keywords" content="Validator Signup Form Responsive Widget,Login form widgets, Sign up Web forms , Login signup Responsive web form,Flat Pricing table,Flat Drop downs,Registration Forms,News letter Forms,Elements"
	/>
	<script>
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
	</script>
	<!-- Meta tag Keywords -->
	<!-- css file -->
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
	<!-- Style-CSS -->
	<!-- //css file -->
	<!-- web-fonts -->
	<link href="//fonts.googleapis.com/css?family=Cuprum:400,400i,700,700i&amp;subset=cyrillic,cyrillic-ext,latin-ext,vietnamese"
	    rel="stylesheet">
	<!-- //web-fonts -->

</head>

<body>
	<!-- title -->
	<h1>
		<span>V</span>alidator
		<span>S</span>ignup
		<span>F</span>orm
	</h1>
	<!-- //title -->
	<!-- content -->
	<div class="sub-main-w3">
		<form validate="true" action="/signup" method="post">
			<div class="form-group">
				<label for="exampleInputText">Name</label>
				<input type="text" class="form-control" name="name" placeholder="Enter Name" required>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Email address</label>
				<input type="email" class="form-control" name="email" aria-describedby="emailHelp" placeholder="Enter email" required>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Password</label>
				<input maxlength="10" minlength="3" type="password" name="password" class="form-control"  placeholder="Password"
				    required>
			</div>
			<div class="form-group">
				<label for="exampleConfirmPassword1">Confirm Password</label>
				<input type="password" class="form-control" id="exampleConfirmPassword1" name="confirmationPassword" placeholder="Confirm Password" required>
			</div>
			<div class="form-group">
				<label for="gender">Gender</label>
				<select name="gender">
					<#list genderList as gender>
	  					<option value="${gender.name()}">${gender.description}</option>
	  				</#list>
	  			</select>
			</div>
			<div class="form-group">
				<label for="age">Age</label>
				<input type="number" class="form-control" name="age" id="age" placeholder="Enter Age" required>
			</div>
			<div class="form-group">
				<label class="checkbox-inline">
					<input type="checkbox" value="true" required>I agree to the terms and conditions
				</label>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<!-- //content -->

	<!-- copyright -->
	<div class="footer">
		<p>&copy; 2018 Validator Signup Form. All rights reserved | Design by
			<a href="http://w3layouts.com">W3layouts</a>
		</p>
	</div>
	<!-- //copyright -->

	<!-- Jquery -->
	<script src="js/jquery-2.2.3.min.js"></script>
	<!-- //Jquery -->
	<!-- Jquery -->
	<script src="js/jquery-simple-validator.min.js"></script>
	<!-- //Jquery -->

</body>

</html>