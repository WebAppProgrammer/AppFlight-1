<%@ page errorPage="exception.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" rel="stylesheet" href="css/standard.css" />
		<title>New User Registration</title>		
	</head>
<body>
<div id="canvas">
<div id="whole">
	<header>
	<img alt="Logo" class="float-left" src="images/logo.png" />
	<h1>Flight Booking System</h1>
	<table id="menubar">
		<tr>
			<td id="first"></td>
			<td id="second"></td>
			<td id="third"></td>
			<td id="fourth"></td>
		</tr>
	</table>
</header>
	<div id="content">
		<div class="float-right">
			<div id="registrationdiv">
				<h3 class="form-title">New User Registration</h3>
				<form id="registration-form" action="MainCtrl" method="POST">
					<table>
						<tr>
							<td class="der">First Name:</td>
							<td><input type="text" name="first" value="${registration.first}" required /></td>
						</tr>
						<tr>
							<td class="der">Last Name:</td>
							<td><input type="text" name="last" value="${registration.last}" required /></td>
						</tr>
						<tr>
							<td class="der">e-mail:</td>
							<td><input type="email" name="email" value="${registration.email}" required /></td>
						</tr>
						<tr>
							<td class="der">Password:</td>
							<td><input type="password" id="regularpass" name="password" required /></td>
						</tr>
						<tr>
							<td class="der">Confirm Password:</td>
							<td><input type="password" id="confirmpass" name="confirm" required /></td>
						</tr>
						<tr>
							<td class="der">Phone:</td>
							<td><input type="text" name="phone" placeholder="5551234567" value="${registration.phone}" required /></td>
						</tr>			
						<tr>
							<td class="der"><input type="hidden" name="action" value="registration" /></td>
							<td><input class="button" type="button" value="Register" onclick="matchPassword()" /></td>
						</tr>
					</table>
				</form>
				<p id="feedback">${registration.feedback}</p>
			</div>
		</div>
		<div>
			<img src="images/passenger.jpg" alt="Passenger" width="600" height="600" id="homeimg" />
		</div>
		<script type="text/javascript"> <!--
			function matchPassword() {
				document.getElementById("feedback").innerHTML = "";
				var inPassword = document.getElementById("regularpass").value;
				var inConfirm = document.getElementById("confirmpass").value;
				if (inPassword != inConfirm) {
					document.getElementById("feedback").innerHTML = "These passwords don't match.";
				}else{
					document.getElementById("registration-form").submit();
				}
			}
	-->	</script>
	</div>
	<jsp:include page="footer.html" />
</div><!-- id="whole" -->
</div><!-- id="canvas" -->
</body>
</html>