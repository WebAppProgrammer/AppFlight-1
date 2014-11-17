package controllers;

import java.io.IOException;
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
 * Servlet implementation class ShoppingCartCtrl
 */
public class ShoppingCartCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCartCtrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//check to see if the session has a shopping cart
		String feedback=""; 
		HttpSession session= request.getSession(); 
		//Create a shopping cart
		ShoppingCart shoppingCart= (ShoppingCart)session.getAttribute("shoppingCart"); 
		FlightQuery searchQ= (FlightQuery)session.getAttribute("searchQ");
		String event= request.getParameter("event");
		if(request.getParameter("flight_id")==null){
			System.out.println("null flight id");
		}
		int flight_id= Integer.parseInt(request.getParameter("flight_id"));
		@SuppressWarnings("unchecked")
		Map<Integer, Flight> flights =(TreeMap<Integer, Flight>) session.getAttribute("flights");
		System.out.println(""+searchQ);
		if(event.equals("remove")){			
			if(!shoppingCart.RemoveEntry(flight_id)){
				feedback= "We were not able to remove your flight at this time!"; 
				response.setStatus(HttpServletResponse.SC_SEE_OTHER);
			}else {
				response.setStatus(HttpServletResponse.SC_OK);
			}
		}
		
		if(event.equals("update")){
			int seats= Integer.parseInt(request.getParameter("seats"));
			Map<Integer, ShoppingCartEntry> entries = shoppingCart.getEntries();
			ShoppingCartEntry entry= entries.get(flight_id); 
			int current = entry.getPassengers();
			if(seats > current){
				int toAdd = seats-current;
				Flight flight = shoppingCart.getEntries().get(flight_id).getFlight();
				if(flight.isOverbook(entry.getCabin(), toAdd)){
					feedback = "The amount of seats you requested is unavailable.";
					response.setStatus(HttpServletResponse.SC_SEE_OTHER);
				}else{
					for (int i=0; i < (toAdd); i++){
						entry.addOneSeats();
					}
					feedback = "added";
					response.setStatus(HttpServletResponse.SC_OK);
				}
			}else if(seats < current){
				for (int i=0; i < (current - seats); i++){
					entry.removeOneSeats();
				}
				response.setStatus(HttpServletResponse.SC_OK);
			}else{
				feedback = "Stop pressing update!";
				response.setStatus(HttpServletResponse.SC_SEE_OTHER);
			}
		}

		request.setAttribute("feedback", feedback);
		System.out.println(shoppingCart);
		session.setAttribute("shoppingCart", shoppingCart);
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(feedback);
		System.out.println(feedback);
	}

}
