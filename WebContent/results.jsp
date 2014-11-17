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
		<title>Select Your Flight</title>		
	</head>
<body>
<div id="canvas">
<div id="whole">
	<%@ include file="header.jsp" %>
	<div id="content">
		<div id="results-div">
			<h1>Search Results</h1>
			<c:forEach items="${flights}" var="f1" begin="0" end="0" step="1">
    			<h2>From ${f1.value.sourceCity} to ${f1.value.destinCity}</h2>
			</c:forEach>
			<table id="results-table">
				<tr>
					<th>Airline</th>
					<th>Flight number</th>
					<th>Departure time</th>
					<th>Arrival time</th>
					<th>Stops</th>
					<th id="price-label">Price per Seat</th>
				</tr>
			<c:forEach items="${flights}" var="entry">
					<tr>
					    <td>${entry.value.operatorName}</td>
					    <td>${entry.value.flight_id}</td>
					    <td>${entry.value.departureTime}</td>
					    <td>${entry.value.arrivalTime}</td>
					    <td>${entry.value.stops}</td>
					    <td class="price">
					    	<form action="MainCtrl" method="POST">
					    		<input type="hidden" name="flight_id" value="${entry.value.flight_id}" />
					    		<input type="hidden" name="action" value="selectf" />
					    		<input class="button" type="submit" value="${entry.value.price}&#10;Continue &#9656;" />
						    </form>
					    </td>
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