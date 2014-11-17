<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" rel="stylesheet" href="css/standard.css" />
		<title>We are sorry!</title>		
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
		<h1>We are sorry!</h1>
		<h2>There is a problem processing your request at this time. Please, 
			<a href="">log in again</a> to the website and try again. 
			The issue has been reported to our technical support group, 
			and they will be working to make sure this won't happen again.</h2>
		<ul>
			<li><h2>${pageContext.errorData.throwable}</h2></li>
		</ul>
	</div>
	<jsp:include page="footer.html" />
</div><!-- id="whole" -->
</div><!-- id="canvas" -->
</body>
</html>