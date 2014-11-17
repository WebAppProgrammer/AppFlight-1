<%@ page errorPage="exception.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" rel="stylesheet" href="css/standard.css" />
		<title>Flight Search</title>	
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
		<script>
			$(function() {
				$( "#departure" ).datepicker({minDate : 0});
				$( "#arrival" ).datepicker({minDate : 0});
			});
		</script>	
	</head>
<body>
<div id="canvas">
<div id="whole">
	<%@ include file="header.jsp" %>
	<div id="content">
		<div class="float-right" id="search-div">
			<h3 class="form-title">Flight Search Query</h3>
			<form action="MainCtrl" name="search-form" id="search-form"
				method="post">
				<table id="search_flight_query">
					<tr>
						<td colspan="4">
							<input type="radio" name="triptype" onClick="showAll()" id="roundtrip" value="Roundtrip" />Round trip&#160;&#160;&#160;&#160;&#160;
							<input type="radio" name="triptype" onClick="hide1()" id="oneway" value="OneWay" checked />One Way&#160;&#160;&#160;&#160;
							<input type="radio" name="triptype" onClick="showAll()" id="multicity" value="Multicity" />Multiple Cities
						</td>
					</tr>
					<tr class="paddedrow">
						<td>From</td>
						<td title="Coming soon" class="subtext">See all cities&#8599;&#10697;</td>
						<td>To</td>
						<td title="Coming soon" class="subtext">See all cities&#8599;&#10697;</td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" name="source" placeholder="Airport" required value="${searchQ.source}" /></td>
						<td colspan="2"><input type="text" name="destination" placeholder="Airport" required value="${searchQ.destination}" /></td>
					</tr>
					<tr class="paddedrow">
						<td colspan="2">Departing</td>
						<td colspan="2"><span id="arrival_title">Returning</span></td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" id="departure" name="departure" placeholder="mm/dd/yy" required value="${searchQ.departure}" /></td>
						<td colspan="2"><input type="text" id="arrival" name="arrival" placeholder="mm/dd/yy" value="${searchQ.arrival}" /></td>
					</tr>
					<tr class="paddedrow">
						<td colspan="2">Passengers</td>
						<td colspan="2">Cabin</td>
					</tr>							
					<tr>
						<td colspan="2">
							<input type="number" name="seats" min="1" max="100" value="${(empty searchQ.seats) ? 1 : searchQ.seats }" />
						</td>
						<td>
							<select id="Class" name="flightClass">
								<option value="Economy">Economy</option>
								<option value="Business">Business</option>
								<option value="FirstClass">First Class</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="4" id="feedback">${searchQ.feedback}</td>
					</tr>
				</table>
				<p class="der">
					<input type="submit" name="Search" value="Find a Flight" class="button" />
				</p>
				<input type="hidden" name="action" value="flight_search" />
			</form>
		</div>
		<div>
			<img src="images/liftoff.jpg" alt="Airplane lift off" width="600" height="600" id="homeimg" />
		</div>
	</div>
	<jsp:include page="footer.html" />
</div><!-- id="whole" -->
</div><!-- id="canvas" -->
	<script><!--
		document.getElementById("oneway").checked=true;
		hide1();
	
		function showAll() {
	        document.getElementById("arrival").style.display='';
	        document.getElementById("arrival_title").style.display='';
		}
		
		function hide1() {
	        document.getElementById("arrival").style.display='none';
	        document.getElementById("arrival_title").style.display='none';
		}
	--></script>
</body>
</html>