<%@ page errorPage="exception.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" rel="stylesheet" href="css/standard.css" />
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<title>Seat Selection</title>		
	</head>
<body>
<div id="canvas">
<div id="whole">
	<%@ include file="header.jsp" %>
	<div id="content">
		<div id="results-div">
			<h1>Flight Details</h1>
			<h2>From ${flight.sourceCity} to ${flight.destinCity}</h2>
			<form action="MainCtrl" method="POST" id="seatform">
				<table id="results-table">
					<tr>
						<th>Airline</th>
						<th>Flight number</th>
						<th>Departure time</th>
						<th>Arrival time</th>
						<th>Stops</th>
						<th id="price-label">Price per Seat</th>
					</tr>
					<!-- Loop through flights -->
					<tr>
						<td>${flight.operatorName}</td>
						<td>${flight.flight_id}</td>
						<td>${flight.departureTime}</td>
						<td>${flight.arrivalTime}</td>
						<td>${flight.stops}</td>
						<td class="price">
							<input type="hidden" name="flight_id" value="${flight.flight_id}" />
							<input class="button" type="submit" value="${flight.price}&#10;Add to Cart">
						</td>
					</tr>
				</table>
				<div><p id="feedback">&#160;</p></div>
				<div class="float-right" id="seatcount-div">
					<input class="button" type="button" onclick="goToResults();" value="&#9666;Back to&#10;results">
					<input class="button" type="button" onclick="goToCart();" value="Check &#9656;&#10;out">
					<h3>Seats you can select</h3>
					<div id="seatcount">
						<div id="s-count">
							<span id="the-count">${searchQ.seats}</span>
						</div>
					</div>
				</div>
				<div id="fuselage">
				<table>
					<tr>
						<th class="fuselcolhd"></th>
						<th>1</th>
						<th>2</th>
						<th>3</th>
						<th>4</th>
						<th>5</th>
						<th>6</th>
						<th>7</th>
						<th>8</th>
						<th>9</th>
						<th>10</th>
					</tr>
					<tr>
						<th class="fuselcolhd">A</th>
						<td><input type="checkbox" name="seat" value="1A"></td>
						<td><input type="checkbox" name="seat" value="2A" disabled checked></td>
						<td><input type="checkbox" name="seat" value="3A"></td>
						<td><input type="checkbox" name="seat" value="4A" disabled checked></td>
						<td><input type="checkbox" name="seat" value="5A" disabled checked></td>
						<td><input type="checkbox" name="seat" value="6A"></td>
						<td><input type="checkbox" name="seat" value="7A" disabled checked></td>
						<td><input type="checkbox" name="seat" value="8A"></td>
						<td><input type="checkbox" name="seat" value="9A" disabled checked></td>
						<td><input type="checkbox" name="seat" value="10A"></td>
					</tr>
					<tr>
						<th class="fuselcolhd">B</th>
						<td><input type="checkbox" name="seat" value="1B" disabled checked></td>
						<td><input type="checkbox" name="seat" value="2B"></td>
						<td><input type="checkbox" name="seat" value="3B" disabled checked></td>
						<td><input type="checkbox" name="seat" value="4B" disabled checked></td>
						<td><input type="checkbox" name="seat" value="5B" disabled checked></td>
						<td><input type="checkbox" name="seat" value="6B"></td>
						<td><input type="checkbox" name="seat" value="7B"></td>
						<td><input type="checkbox" name="seat" value="8B" disabled checked></td>
						<td><input type="checkbox" name="seat" value="9B" disabled checked></td>
						<td><input type="checkbox" name="seat" value="10B"></td>
					</tr>
					<tr>
						<th class="fuselcolhd">C</th>
						<td><input type="checkbox" name="seat" value="1C"></td>
						<td><input type="checkbox" name="seat" value="2C" disabled checked></td>
						<td><input type="checkbox" name="seat" value="3C"></td>
						<td><input type="checkbox" name="seat" value="4C"></td>
						<td><input type="checkbox" name="seat" value="5C" disabled checked></td>
						<td><input type="checkbox" name="seat" value="6C"></td>
						<td><input type="checkbox" name="seat" value="7C" disabled checked></td>
						<td><input type="checkbox" name="seat" value="8C" disabled checked></td>
						<td><input type="checkbox" name="seat" value="9C"></td>
						<td><input type="checkbox" name="seat" value="10C"></td>
					</tr>
					<tr>
						<th class="fuselcolhd">D</th>
						<td><input type="checkbox" name="seat" value="1D" disabled checked></td>
						<td><input type="checkbox" name="seat" value="2D" disabled checked></td>
						<td><input type="checkbox" name="seat" value="3D" disabled checked></td>
						<td><input type="checkbox" name="seat" value="4D" disabled checked></td>
						<td><input type="checkbox" name="seat" value="5D" disabled checked></td>
						<td><input type="checkbox" name="seat" value="6D" disabled checked></td>
						<td><input type="checkbox" name="seat" value="7D" disabled checked></td>
						<td><input type="checkbox" name="seat" value="8D"></td>
						<td><input type="checkbox" name="seat" value="9D"></td>
						<td><input type="checkbox" name="seat" value="10D"></td>
					</tr>
				</table>
				</div>
				<input type="hidden" name="action" value="addShopCart" />
			</form>
		</div>
	</div>
	
	<jsp:include page="footer.html" />
</div><!-- id="whole" -->
</div><!-- id="canvas" -->
<script>
$( "#seatform" ).submit(function( event ){
	// Stop form from submitting normally
	  event.preventDefault();
	  $( "#feedback" ).empty().append("Processing...");
	 
	  // Get some values from elements on the page:
	  var $form = $( this ),
	    action = $form.find( "input[name='action']" ).val(),
	    flight_id = $form.find( "input[name='flight_id']" ).val(),
	    seats = $form.find( "input[name='seat']" ).val(),
	    url = $form.attr( "action" );
	 
	  // Send the data using post
	  var posting = $.post( url, { action:action, flight_id:flight_id, seats:seats }, function( data ) {
		  $( "#feedback" ).empty().append(data);
	  });
	 
	});

function goToCart(){
	window.location.replace("shoppingcart.jsp");
}

function goToResults(){
	window.location.replace("results.jsp");
}
</script>
</body>
</html>