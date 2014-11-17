package controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.*;
import model.*;

/**
 * Servlet implementation class ViewBookCtrl
 */
public class ViewBookCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBookCtrl() {
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
		// Retrieving session objects
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<Integer, Flight> flights = (TreeMap<Integer, Flight>)session.getAttribute("flights");
		FlightQuery searchQ = (FlightQuery)session.getAttribute("searchQ");
		User user = (User)session.getAttribute("user");

		int flight_id = Integer.parseInt(request.getParameter("flight_id"));
		Flight flight = flights.get(flight_id);
		request.setAttribute("flight", flight);
		
		//Check seat availability
		//Note: seat information is recorded, but unused. In reality, this information is sent to airline.
		String[] seats = request.getParameterValues("seat");
		String seatOut = "";
		if(seats!=null){
			if(seats.length == 1){
				seatOut = seats[0];
			}else{
				for(String s : seats){
					seatOut += s + ", ";
				}
				seatOut = seatOut.substring(0, seatOut.length() - 2);
			}
		}
		flight.isOverbook(searchQ.getFlightClass(), searchQ.getSeats());
		
		//Get total cost
		double total = flight.getCost()*searchQ.getSeats();
		
		Calendar cal = GregorianCalendar.getInstance();
		Booking booking = new Booking(0, cal, flight_id, FlightClass.valueOf(searchQ.getFlightClass()), searchQ.getSeats(), seatOut, 0, user.getUserId(), total);
		TransactionQuery tQuery = new TransactionQuery();
		
		session.setAttribute("booking", booking);
		request.setAttribute("tQuery", tQuery);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/transaction.jsp");
	    dispatcher.forward(request, response);
	}
}
