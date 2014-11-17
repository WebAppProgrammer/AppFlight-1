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
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<title>Select Your Flight</title>		
	</head>
<body>
<div id="canvas">
<div id="whole">
	<%@ include file="header.jsp" %>
	<div id="content">
		<div id="results-div">
			<h1>Shopping Cart</h1>
			<div id="ajaxholder">
			<table id="results-table">
				<tr>
					<th>Flight number</th>
					<th>Departure</th>
					<th>Destination</th>
					<th>Departure time</th>
					<th>Arrival time</th>
					<th>Price</th>
					<th>Seats</th>
					<th>Total</th>
					<th id="price-label">Options</th>
				</tr>
			<c:forEach items="${shoppingCart.entries}" var="entry" varStatus="ts" >						
						<tr>
						    <td>${entry.value.flight.operator}${entry.value.flight.flight_id}</td>
						    <td>${entry.value.flight.sourceCity}</td>
						    <td>${entry.value.flight.destinCity}</td>    
						    <td>${entry.value.flight.departureTime}</td>
						    <td>${entry.value.flight.arrivalTime}</td>
						    <td>${entry.value.flight.price}</td>
						    <td>
	    						<form id="updatef${ts.count}" action="MainCtrl" method="POST">
							    	<input type="number" name="seats" min="1" max="100" value="${entry.value.passengers}" />
						    		<input type="hidden" name="flight_id" value="${entry.value.flight.flight_id}" />
						    		<input type="hidden" name="action" value="update" />
						    		<input type="hidden" name="event" value="update" />
							    	<input class="button" id="update${ts.count}" type="button" value="Update" />
				    			</form>
				    		</td>			    								    	
						    <td>${entry.value.price}</td>
						    <td>
						    	<form id="deletef${ts.count}" action="MainCtrl" method="POST">
						    		<input type="hidden" name="flight_id" value="${entry.value.flight.flight_id}" />
						    		<input type="hidden" name="action" value="update" />
						    		<input type="hidden" name="event" value="remove" />
						    		<input type="hidden" name="seats" value="0" />
						    		<input class="button" id="delete${ts.count}" type="button" value="Delete" />							    		
						    	</form>
				    		</td> 	
				    	</tr>
			</c:forEach>
			</table>
			<h2>Grand Total: ${shoppingCart.total}</h2>
			<span id="feedback"></span>
			</div><!-- ajaxholder -->
			<div class="float-right" id="seatcount-div">
				<input class="button" type="button" onclick="goToSearch();" value="&#9666;Continue&#10;Shopping">
				<input class="button" type="button" onclick="goToCheckOut();" value="Check &#9656;&#10;out">
			</div>
		</div>
	</div>
	<jsp:include page="footer.html" />
</div><!-- id="whole" -->
</div><!-- id="canvas" -->
<script>


//$("#results-div").delegate(".button", "click", function () {
	 
//	$(this).closest('tr').remove(); 
 
//});

$( '.button' ).click(function(){
	var $form = $(this).parent(),
    action = $form.find( "input[name='action']" ).val(),
    event = $form.find( "input[name='event']" ).val(),
    seats = $form.find( "input[name='seats']" ).val(),
    flight_id = $form.find( "input[name='flight_id']" ).val(),
    url = $form.attr( "action" );
	
	// Send the data using post
	  var posting = $.post( url, { action:action, event:event, 
		  	seats:seats, flight_id:flight_id });
	  
	  posting.done(function() {
		  		$( "#feedback" ).empty();
		  		$( "#ajaxholder" ).empty();
				$( "#ajaxholder" ).load("shoppingcart.jsp #ajaxholder");
			})
	
	  posting.fail(function() {
		  		$( "#feedback" ).empty();
		  		$( "#ajaxholder" ).empty();
				$( "#ajaxholder" ).load("shoppingcart.jsp #ajaxholder");
			})
	
})

$( "form" ).submit(function(){
	var form = $(this);
})

function deleteflight(){
	var form= this.parent(); 
	var id= form.id;
	alert(form.attr('id')); 
}


function goToSearch(){
	window.location.replace("search.jsp");
}

function goToCheckOut(){
	window.location.replace("results.jsp");
}; 

</script>


</body>
</html>