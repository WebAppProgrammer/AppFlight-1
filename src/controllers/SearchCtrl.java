package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.FlightQuery;
import data.FlightsDA;
import model.Airport;
import model.Flight;

/**
 * Servlet implementation class SearchCtrl
 */
public class SearchCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchCtrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		session.removeAttribute("searchQ");
        boolean isInvalid = true;
		String url = "/search.jsp";
		String feedback = "";
		
		//Reading parameters
		String source = request.getParameter("source").trim().toUpperCase();
		String destination = request.getParameter("destination").trim().toUpperCase();
		String departure = request.getParameter("departure").trim();
		String arrival = request.getParameter("arrival").trim();
		int seats = Integer.parseInt(request.getParameter("seats"));
		String flightClass = request.getParameter("flightClass");

		//Data validation (server side)
        if(source.isEmpty()){
        	feedback = "Please provide the departure city";
        }else if(!Airport.isValid(source)){
        	feedback = "Please provide a valid departure airport";
        }else if(destination.isEmpty()){
        	feedback = "Please provide the destination city";
        }else if(!Airport.isValid(destination)){
        	feedback = "Please provide a valid destination airport";
        }else if(departure.isEmpty()){
        	feedback = "Please provide the departure date";
        }else{
        	isInvalid = false;
        }
        
        //Process form
        FlightQuery searchQ = new FlightQuery(source, destination, departure, arrival, seats, flightClass, feedback);
        if(!isInvalid){
        	//query database for flights
        	Map<Integer, Flight> flights = new TreeMap<Integer, Flight>();
			try{
	        	FlightsDA.setUpConnectionPool("cse.unl.edu", "cse464", "wong", "booking");
				flights = FlightsDA.retrieveOneWayFlights(searchQ);
				if(flights.size()>0){
					session.setAttribute("flights", flights);
					url = "/results.jsp";
				}else{
					feedback = "No flight matchs your search";
					searchQ.setFeedback(feedback);
				}
			}catch (NumberFormatException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        session.setAttribute("searchQ", searchQ);
        RequestDispatcher dispatcher;
	    dispatcher = getServletContext().getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}
}
