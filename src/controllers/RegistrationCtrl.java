package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.RegistrationEntry;
import data.*;
import model.User;
import utilities.Crypto;
import utilities.DataValidator;

/**
 * Servlet implementation class RegistrationCtrl
 */
public class RegistrationCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationCtrl() {
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
		response.setContentType("text/html;charset=UTF-8");
        boolean isInvalid = true;
		String url = "/registration.jsp";
		String feedback = "";
		
		//Reading parameters
        String first = request.getParameter("first").trim();
        String last = request.getParameter("last").trim();
        String phone = request.getParameter("phone").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String confirm = request.getParameter("confirm").trim();
        
        //Data validation (server side)
        if(first.isEmpty()){
        	feedback = "Please provide your first name";
        }else if(last.isEmpty()){
        	feedback = "Please provide your last name";
        }else if(phone.isEmpty()){
        	feedback = "Please provide your phone";
        }else if(!DataValidator.isInteger(phone)){
        	feedback = "Please input your phone number as requested";
        }else if(email.isEmpty()){
        	feedback = "Please provide your e-mail";
    	}else if(!DataValidator.isValidEmail(email)){
        	feedback = "Please provide a valid e-mail";
    	}else if(password.isEmpty()){
        	feedback = "Please provide a password";
        }else if(confirm.isEmpty()){
        	feedback = "Please confirm your password";
    	}else if(!password.equals(confirm)){
        	feedback = "The passwords don't match";
        }else{
        	isInvalid = false;
        }
        
        try {
        	UsersDA.setUpConnectionPool("cse.unl.edu", "wong", "wong", "booking");
			if(UsersDA.isEmailTaken(email)){
				feedback = "The e-mail is already registered";
				isInvalid = true;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			isInvalid = true;
			feedback = "There was a problem processing your request. Please try again.";
			e1.printStackTrace();
		}
        
        //Process form
        if(isInvalid){
            RegistrationEntry registration = new RegistrationEntry(first, last, email, phone, feedback);
            request.setAttribute("registration", registration);
        }else{
        	try {
            	//instantiate user
            	String hashPass = Crypto.hash(password);
            	User user = new User(0, first, last, email, hashPass, phone);
        		UsersDA.setUpConnectionPool("cse.unl.edu", "wong", "wong", "booking");
				UsersDA.insertUser(user);
				url = "/index.jsp";
				System.out.println("inserted user to DB");
			}catch (SQLException e) {
				e.printStackTrace();
				url = "/exception.jsp";
			}
        }
    	RequestDispatcher dispatcher;
	    dispatcher = getServletContext().getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}
}
