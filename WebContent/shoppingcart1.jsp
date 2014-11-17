<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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