package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.UsersDA;
import model.User;
import utilities.CookiesUtilities;
import utilities.Crypto;

/**
 * Servlet implementation class LoginCtrl
 */
public class LoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCtrl() {
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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String hashPass = Crypto.hash(password);
		String feedback = "";
		String url = "/index.jsp";
		try{
			UsersDA.setUpConnectionPool("cse.unl.edu", "wong", "wong", "booking");
			if(UsersDA.isLoginValid(email, hashPass)){
				UsersDA.setUpConnectionPool("cse.unl.edu", "wong", "wong", "booking");
				User user = UsersDA.retrieveUser(email);
				HttpSession session = request.getSession();
				session.invalidate();
				session = request.getSession();
				session.setMaxInactiveInterval(30*60);
				session.setAttribute("user", user);
				//Login successful; setting cookie
				String cookieCheck = request.getParameter("cookie");
				if(cookieCheck == null){
					Cookie cookie = CookiesUtilities.getCookie(request, "email");
					if(cookie != null){
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}else{
					Cookie emailCookie = new Cookie("email", user.getEmail());
		            emailCookie.setMaxAge(30*60*60);
		            response.addCookie(emailCookie);
				}
				url = "/search.jsp";
			}else{
				feedback = "Please verify your password";
				request.setAttribute("email", email);
				request.setAttribute("feedback", feedback);
			}
		}catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			url = "/exception.jsp";
		}finally{
			RequestDispatcher dispatcher;
		    dispatcher = getServletContext().getRequestDispatcher(url);
		    dispatcher.forward(request, response);
		}
	}
}
