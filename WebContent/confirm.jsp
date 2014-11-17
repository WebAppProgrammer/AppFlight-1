<%@ page errorPage="exception.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" rel="stylesheet" href="css/standard.css" />
		<title>Transaction completed successfully!</title>		
	</head>
<body>
<div id="canvas">
<div id="whole">
	<%@ include file="header.jsp" %>
	<div id="content">
		<div id="ticket-div">
			<h1>e-Ticket for ${booking.passenger}</h1>
			<table id="ticket">
				<tr class="ticketrow1">
					<td colspan="5">${booking.cabinName} Cabin</td>
					<td colspan="2" class="ticketcol6"></td>
				</tr>
				<tr>
					<td class="ticketcol1">From</td>
					<td colspan="2"></td>
					<td class="ticketcol4">Airline</td>
					<td class="ticketcol4">Flight</td>
					<td colspan="2" class="ticketcol6"></td>
				</tr>
				<tr>
					<td></td>
					<td class="ticketcol2">${flight.sourceCity}</td>
					<td class="ticketcol3">${flight.departureTime}</td>
					<td class="center">${flight.operatorName}</td>
					<td class="center">${flight.flight_id}</td>
					<td class="ticketcol6">Airline Ticket</td>
					<td class="ticketcol7">${flight.price}</td>
				</tr>
				<tr>
					<td class="ticketcol1">To</td>
					<td colspan="2"></td>
					<td colspan="2" class="center"></td>
					<td class="ticketcol6">passengers(${booking.number_of_seats})</td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td class="ticketcol2">${flight.destinCity}</td>
					<td class="ticketcol3">${flight.arrivalTime}</td>
					<td colspan="2" class="ticketcol4">Stops</td>
					<td rowspan="2" class="ticketcol6">Total due at booking</td>
					<td rowspan="2" class="ticketcol7">${booking.total}</td>
				</tr>
				<tr>
					<td></td>
					<td class="ticketcol1">Duration</td>
					<td class="ticketcol3">${flight.duration}</td>
					<td colspan="2" class="center">1</td>
				</tr>
			</table>
			<form action="MainCtrl" method="POST" id="printform" target="_blank">
				<input type="radio" name="redirect" id="printer" value="printer" checked>Printer
				<input type="radio" name="redirect" id="pdf" value="pdf" >PDF
				<input type="hidden" name="action" value="printpdf">
				<input type="button" onclick="send2printer()" class="button" value="Print" />			
			</form>
		</div>
	</div>
	<jsp:include page="footer.html" />
</div><!-- id="whole" -->
</div><!-- id="canvas" -->
	<script><!--
		function send2printer() {
			if(document.getElementById("printer").checked){
				window.print();
			}else{
				document.getElementById('printform').submit();
			}
		}
	--></script>
</body>
</html>