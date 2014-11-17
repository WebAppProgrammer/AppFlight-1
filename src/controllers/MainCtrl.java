package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainCtrl
 */
public class MainCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainCtrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	private enum Action{
		addShopCart, flight_search, history, login, logout, printpdf, registration, reserve, selectf, transaction , update
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	String actionParam = request.getParameter("action");
    	RequestDispatcher dispatcher = null;
		if(actionParam != null){
			Action action = Action.valueOf(actionParam);
			switch(action){
			//Add flight to the shopping cart
			case addShopCart:
				dispatcher = getServletContext().getRequestDispatcher("/ManageShoppingCartCtrl");
				dispatcher.forward(request, response);
				break;
			//Search for a flight
			case flight_search:
				dispatcher = getServletContext().getRequestDispatcher("/SearchCtrl");
				dispatcher.forward(request, response);
				break;
			//Show booking history
			case history:
				dispatcher = getServletContext().getRequestDispatcher("/HistoryCtrl");
				dispatcher.forward(request, response);
				break;
			//User trying to log into the system
			case login:
				dispatcher = getServletContext().getRequestDispatcher("/LoginCtrl");
				dispatcher.forward(request, response);
				break;
			//User logging out
			case logout:
				dispatcher = getServletContext().getRequestDispatcher("/LogoutCtrl");
				dispatcher.forward(request, response);
				break;
			//Print info to pdf
			case printpdf:
				dispatcher = getServletContext().getRequestDispatcher("/PrintCtrl");
				dispatcher.forward(request, response);
				break;
			//Registration of a new user
			case registration:
				dispatcher = getServletContext().getRequestDispatcher("/RegistrationCtrl");
				dispatcher.forward(request, response);
				break;
			//Check availability, calculate price & book flight
			case reserve:
				dispatcher = getServletContext().getRequestDispatcher("/ViewBookCtrl");
				dispatcher.forward(request, response);
				break;
			//Selected a flight
			case selectf:
				dispatcher = getServletContext().getRequestDispatcher("/FlightSearchResult");
				dispatcher.forward(request, response);
				break;
			case transaction:
				dispatcher = getServletContext().getRequestDispatcher("/TransactionCtrl");
				dispatcher.forward(request, response);
				break;
				//Update shopping cart
			case update:
				dispatcher = getServletContext().getRequestDispatcher("/ShoppingCartCtrl");
				dispatcher.forward(request, response);
				break;
			default:
				dispatcher = getServletContext().getRequestDispatcher("/");
				dispatcher.forward(request, response);
				break;
			}
		}else{
			//TODO Send back to default
	    	System.out.println("Action not set");
	    	dispatcher = getServletContext().getRequestDispatcher("/");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
