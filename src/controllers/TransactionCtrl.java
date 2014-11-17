package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.AccountsDA;
import data.BookingsDA;
import model.*;
import beans.TransactionQuery;
import utilities.DataValidator;

/**
 * Servlet implementation class TransactionCtrl
 */
public class TransactionCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionCtrl() {
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
		//Get parameters
		HttpSession session = request.getSession();
		int flightId = Integer.parseInt(request.getParameter("flight_id"));
		Booking booking = (Booking)session.getAttribute("booking");
		@SuppressWarnings("unchecked")
		TreeMap<Integer, Flight> flights = (TreeMap<Integer, Flight>)session.getAttribute("flights");
		request.setAttribute("flight", flights.get(flightId));
		String url = "/transaction.jsp";
		String holder = request.getParameter("AccountHolder").trim();
		String accountParam = request.getParameter("AccountNumber").trim();
		String routingParam = request.getParameter("RoutingNumber").trim();
		String passenger = request.getParameter("passenger").trim();
		String dob = request.getParameter("dob").trim();
		String gender = request.getParameter("gender").trim();
		
		String feedback = "";
		boolean isInvalid = true;
		boolean isTransactionOK = false;

		if(holder.isEmpty()){
			feedback = "Please provide the account holder";
		}else if(accountParam.isEmpty()){
			feedback = "Please provide the account number";
		}else if(!DataValidator.isInteger(accountParam)){
			feedback = "Please verify the account number";
		}else if(routingParam.isEmpty()){
			feedback = "Please provide the routing number";
		}else if(!DataValidator.isInteger(routingParam)){
			feedback = "Pleave verify the routing number";
		}else if(passenger.isEmpty()){
			feedback = "Please provide the main passenger name";
		}else if(dob.isEmpty()){
			feedback = "Please provide the day of birth";
		}else if(gender.isEmpty()){
			feedback = "Please specify the gender";
		}else{
			isInvalid = false;
		}

		TransactionQuery tQuery = new TransactionQuery(holder, accountParam, routingParam, passenger, dob, feedback);
		Account account = null;
		
		if(isInvalid){
			request.setAttribute("tQuery", tQuery);
		}else{
			int accountNumber = Integer.parseInt(accountParam);
			int routingNumber = Integer.parseInt(routingParam);
			
			try {
				AccountsDA.setUpConnectionPool("cse.unl.edu", "wong", "wong", "booking");
				account = AccountsDA.retrieveAccount(holder, accountNumber, routingNumber);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(account != null){
				if(account.isOverdraft(booking.getTotal_cost())){
					feedback = "Unsufficient funds to complete the transaction";
					tQuery.setFeedback(feedback);
					request.setAttribute("tQuery", tQuery);
				}else{
					booking.setAccount_id(accountNumber);
					try {
						//Charge amount
						account.charge(booking.getTotal_cost());
						AccountsDA.setUpConnectionPool("cse.unl.edu", "wong", "wong", "booking");
						AccountsDA.updateAccount(account);
						//Create transaction
						BookingsDA.setUpConnectionPool("cse.unl.edu", "wong", "wong", "booking");
						BookingsDA.insertBooking(booking);
						isTransactionOK = true;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						feedback = "Unable to complete the transaction";
						tQuery.setFeedback(feedback);
						request.setAttribute("tQuery", tQuery);
					}
				}
				
			}else{
				feedback = "Cannot verify the banking information";
				tQuery.setFeedback(feedback);
				request.setAttribute("tQuery", tQuery);
			}
		}
		
		if(isTransactionOK){
			session.setAttribute("account", account);
			booking.setPassenger(passenger);
			booking.setDob(dob);
			booking.setGender(gender);
			request.setAttribute("booking", booking);
			url="/confirm.jsp";
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}
}
