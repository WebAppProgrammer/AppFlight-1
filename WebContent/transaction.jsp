<%@ page errorPage="exception.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" rel="stylesheet" href="css/standard.css" />
		<title>Submit Payment</title>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
	</head>
<body>
<div id="canvas">
<div id="whole">
	<%@ include file="header.jsp" %>
	<div id="content">
		<div id="results-div">
			<h1>Flight Details</h1>
			<h2>From ${flight.sourceCity} to ${flight.destinCity}</h2>
			<form action="MainCtrl" method="POST">
				<table id="results-table">
					<tr>
						<th>Airline</th>
						<th>Flight number</th>
						<th>Departure time</th>
						<th>Arrival time</th>
						<th>Stops</th>
						<th>Passengers</th>
						<th id="price-label">Total Due</th>
					</tr>
					<tr>
						<td>${flight.operatorName}</td>
						<td>${flight.flight_id}</td>
						<td>${flight.departureTime}</td>
						<td>${flight.arrivalTime}</td>
						<td>${flight.stops}</td>
						<td>${booking.number_of_seats}</td>
						<td class="price">
							<input type="hidden" name="flight_id" value="${flight.flight_id}" />
							<input class="button" type="submit" value="${booking.total}&#10;Continue &#9656;">
						</td>
					</tr>
				</table>
				<h3>Banking Information</h3>
				<table>
					<tr>
						<td class="der">Account Holder Name</td>
						<td><input required type="text" name="AccountHolder" required value ="${tQuery.holder}" /></td>
					</tr>
					<tr>
						<td class="der">Account Number</td>
						<td><input required type="text" name="AccountNumber" required value ="${tQuery.account}" /></td>
					</tr>
					<tr>
						<td class="der">Routing Number</td>
						<td><input required type="text" name="RoutingNumber" required value ="${tQuery.routing}" /></td>
					</tr>
				</table>
				<h3>Main Passenger Information</h3>
				<table>
					<tr>
						<td class="der">Full Name</td>
						<td><input type="text" name="passenger" required value ="${tQuery.passenger}" /></td>
					</tr>
					<tr>
						<td class="der">Day of Birth</td>
						<td><input type="text" name="dob" id="datepicker" required placeholder="mm/dd/yy" value ="${tQuery.dob}" />
					</tr>
					<tr>
						<td class="der">Gender</td>
						<td class="izq"><input type="radio" name="gender" value="male">Male<br />
						    <input required type="radio" name="gender" value="female">Female
						</td>
					</tr>
				</table>
				<input type="hidden" name="action" value="transaction" />
			</form>
			<p>${tQuery.feedback}</p>
		</div>
	</div>
	<jsp:include page="footer.html" />
</div><!-- id="whole" -->
</div><!-- id="canvas" -->
	<script type='text/javascript'>
		$(function() {
			$("#datepicker").datepicker({
				maxDate : 0
			});
		});
	</script>
</body>
</html>