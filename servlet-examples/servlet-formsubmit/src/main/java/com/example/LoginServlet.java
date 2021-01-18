package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String validUserId = "michaelwang";
	private static final String validPassword = "helloworld";
	private static final String firstName = "Michael";
	private static final String lastName = "Wang";
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside LoginServlet (get)");
		RequestDispatcher requestDispatcher= request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
        requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside LoginServlet (post)");
		
		// Get the submitted parameters
		String userId = request.getParameter("userid");
		String password = request.getParameter("password");
		
		// Set the request attribute so that the input value can retain in the page
		request.setAttribute("userid", userId);
		request.setAttribute("password", password);
		
		// perform validation
		List<String> errors = null;
		
		// perform empty field checks
		if( userId.isEmpty() ) {
			if( errors == null ) {
				errors = new ArrayList<String>();
			}
			errors.add("Please enter User ID!");
			request.setAttribute("VALIDATION_ERRORS", errors);
		}
		if( password.isEmpty() ) {
			if( errors == null ) {
				errors = new ArrayList<String>();
			}
			errors.add("Please enter Password!");
			request.setAttribute("VALIDATION_ERRORS", errors);
		}
		
		// perform credential comparison
		if( errors == null ) {
			if( userId.compareTo(validUserId) != 0 || password.compareTo(validPassword) != 0 ) {
				errors = new ArrayList<String>();
				errors.add("Invalid credential!");
				request.setAttribute("VALIDATION_ERRORS", errors);
			}
			else {
				// validation passed
				HttpSession session = request.getSession();
	    		System.out.println("session_id = " + session.getId());
	    		session.setAttribute("userid", userId);
	    		session.setAttribute("fname", firstName);
	    		session.setAttribute("lname", lastName);
			}
		}
		
		RequestDispatcher requestDispatcher= (errors == null) ? request.getRequestDispatcher("WEB-INF/jsp/greeting.jsp") :
																request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
        requestDispatcher.forward(request, response);
	}

}
