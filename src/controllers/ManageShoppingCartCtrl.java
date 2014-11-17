package controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.FlightQuery;
import model.Flight;
import model.ShoppingCart;
import model.ShoppingCartEntry;

/**
 * Servlet implementation class ManageShoppingCartCtrl
 */
public class ManageShoppingCartCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageShoppingCartCtrl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//check to see if the session has a shopping cart
		String feedback=""; 
		HttpSession session= request.getSession(); 
		FlightQuery searchQ= (FlightQuery)session.getAttribute("searchQ"); 
		//Create a shopping cart
		ShoppingCart shoppingCart= (ShoppingCart)session.getAttribute("shoppingCart"); 
		if(shoppingCart==null){
			shoppingCart= new ShoppingCart(); 
		}
		int flight_id= Integer.parseInt(request.getParameter("flight_id"));
		@SuppressWarnings("unchecked")
		Map<Integer, Flight> flights =(TreeMap<Integer, Flight>) session.getAttribute("flights"); 
		String[] places= request.getParameterValues("seats"); 
		List<String> seats = Arrays.asList(places);
		Flight flight= flights.get(flight_id); 
		//create a new shopping cart entry 
		ShoppingCartEntry entry= new ShoppingCartEntry(); 
		entry.setFlight(flight);
		entry.setPassengers(searchQ.getSeats());
		entry.setCabin(searchQ.getFlightClass());
		
		//we also need to get the seats 
		entry.setSeats(seats);	
		if(shoppingCart.AddEntry(flight_id, entry)){
			feedback = "The flight was added to your shopping cart."; 
		}else{
			feedback = "The flight you selected is already on your shopping cart.";
		}
		session.setAttribute("shoppingCart", shoppingCart);
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(feedback);
		System.out.println(feedback);
		System.out.println(searchQ.getSeats());
		
	}
}
