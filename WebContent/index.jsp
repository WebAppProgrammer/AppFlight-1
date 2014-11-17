<%@ page errorPage="exception.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" rel="stylesheet" href="css/standard.css" />
		<title>Welcome to the Flight Booking System</title>		
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
			<div id="logindiv">
				<h3 class="form-title">Login to Reserve Your Next Flight</h3>
				<form id="loginform" action="MainCtrl" method="POST">
					<p><input type="email" name="email" placeholder="e-mail" required value="${cookie.email.value}${email}" /></p>
					<p><input type="password" name="password" placeholder="password" required /></p>
					<p><input type="checkbox" name="cookie" value="on" <c:if test="${not empty cookie.email.value}">checked</c:if> />Remember me
					<input type="hidden" name="action" value="login" />
					<input class="button" type="submit" value="Log In" /></p>
				</form>
				<span id="feedback">${feedback}</span>
				<p>New User? <a href="registration.jsp">Click here to register</a></p>
			</div>
		</div>
		<div>
			<img src="images/attendant.jpg" alt="Flight Attendant" width="600" height="600" id="homeimg" />
		</div>
	</div>
	<jsp:include page="footer.html" />
</div><!-- id="whole" -->
</div><!-- id="canvas" -->
</body>
</html>