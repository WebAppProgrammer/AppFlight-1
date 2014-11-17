package controllers;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class FlightSearchResult
 */
public class FlightSearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightSearchResult() {
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
		String url = "/view_and_book.jsp";
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<Integer, Flight> flights = (TreeMap<Integer, Flight>)session.getAttribute("flights");
		int flight_id = Integer.parseInt(request.getParameter("flight_id"));
		session.setAttribute("flight", flights.get(flight_id));
	    response.sendRedirect(this.getServletContext().getContextPath() + url);
//		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
//		dispatcher.forward(request, response);
	}

}
