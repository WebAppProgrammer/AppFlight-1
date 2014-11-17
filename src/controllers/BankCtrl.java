package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Booking;
import utilities.DataValidator;
import beans.BankQuery;
import data.AccountsDA;
import data.BookingsDA;

/**
 * Servlet implementation class BankCtrl
 */
public class BankCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankCtrl() {
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
		Booking booking = (Booking)session.getAttribute("booking");
		String url = "/transaction.jsp";
		String holder = request.getParameter("AccountHolder").trim();
		String accountParam = request.getParameter("AccountNumber").trim();
		String routingParam = request.getParameter("RoutingNumber").trim();
		
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
		}else{
			isInvalid = false;
		}

		BankQuery bQuery = new BankQuery(holder, accountParam, routingParam, feedback);
		Account account = null;
		
		if(isInvalid){
			request.setAttribute("bQuery", bQuery);
		}else{
			int accountNumber = 0;
			int routingNumber = 0;
			try {
				accountNumber = Integer.parseInt(accountParam);
				routingNumber = Integer.parseInt(routingParam);
				AccountsDA.setUpConnectionPool("cse.unl.edu", "wong", "wong", "booking");
				account = AccountsDA.retrieveAccount(holder, accountNumber, routingNumber);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(account != null){
				if(account.isOverdraft(booking.getTotal_cost())){
					feedback = "Unsufficient funds to complete the transaction";
					bQuery.setFeedback(feedback);
					request.setAttribute("bQuery", bQuery);
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
						bQuery.setFeedback(feedback);
						request.setAttribute("tQuery", bQuery);
					}
				}
				
			}else{
				feedback = "Cannot verify the banking information";
				bQuery.setFeedback(feedback);
				request.setAttribute("tQuery", bQuery);
			}
		}
		
		if(isTransactionOK){
			session.setAttribute("account", account);
			request.setAttribute("booking", booking);
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}
}
