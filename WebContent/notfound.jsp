<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" rel="stylesheet" href="css/standard.css" />
		<title>Page not found</title>		
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
		<h1>Resource not found</h1>
		<h2>The resource you are trying to access is unavailable.
			Please verify that the address was typed correctly or
			<a href="">log in again</a> to the website and try again.</h2>
	</div>
	<jsp:include page="footer.html" />
</div><!-- id="whole" -->
</div><!-- id="canvas" -->
</body>
</html>
