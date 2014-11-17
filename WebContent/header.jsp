<jsp:useBean id="user" type="model.User" scope="session" />
<header>
	<img alt="Logo" class="float-left" src="images/logo.png" />
	<ul class="float-right" id="username">
		<li><jsp:getProperty property="first" name="user"/> <span id="drop_icon">&#9660;</span>
			<ul>
				<li><a href="MainCtrl?action=history">Booking History</a></li>
				<li><a href="MainCtrl?action=logout">Log Out</a></li>
			</ul>
		</li>
	</ul>
	<h1>Flight Booking System</h1>
	<table id="menubar">
		<tr>
			<td id="first"><a id="menusearchbtn" href="search.jsp">Flight Search</a></td>
			<td id="second"></td>
			<td id="third"></td>
			<td id="fourth"><a href="shoppingcart.jsp">Shopping Cart</a></td>
		</tr>
	</table>
</header>
