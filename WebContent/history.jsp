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
		<title>Booking History</title>		
	</head>
<body>
<div id="canvas">
<div id="whole">
	<%@ include file="header.jsp" %>
	<div id="content">
		<div id="results-div">
			<h1>Booking History</h1>
			<table id="results-table">
				<tr>
					<th>Date of Booking</th>
					<th>Flight Number</th>
					<th>Number of Seats</th>
					<th>Account Number</th>
					<th>Total Cost</th>
				</tr>
				<!-- Loop through booking history -->
			<c:forEach items="${bookings}" var="b">
				<tr>
				    <td>${b.bookingTime}</td>
				    <td>${b.flight_id}</td>
				    <td>${b.number_of_seats}</td>
				    <td>${b.account_id}</td>
				    <td>${b.total}</td>
			    </tr>
			</c:forEach>

			</table>
		</div>
	</div>
	<jsp:include page="footer.html" />
</div><!-- id="whole" -->
</div><!-- id="canvas" -->
</body>
</html>