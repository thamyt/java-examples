package com.example.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.webclient.ejb.stateless.CalculatorLocal;

/**
 * Servlet implementation class CalculatorServlet
 */
@WebServlet("/CalculatorServlet")
public class CalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//@EJB( lookup = "java:global/ejb-web-client/CalculatorBean!com.example.webclient.ejb.stateless.CalculatorLocal")
	@EJB
	private CalculatorLocal calculator;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/calculator.jsp");
        requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String value1 = request.getParameter("value1");
		String value2 = request.getParameter("value2");
		String operand = request.getParameter("operand");
		
		if( value1.isEmpty() || value2.isEmpty()) {
			request.setAttribute("error", "Please enter the value to compute!");
		}
		else if( operand.length() > 1 ) {
			request.setAttribute("error", "Please select the operand!");
		}
		else {
			try {
				double result = 0.0;
				double a = Double.parseDouble(value1);
				double b = Double.parseDouble(value2);
				
				switch(operand) {
					case "+":	result = calculator.add(a, b); break;
					case "-":	result = calculator.substract(a, b); break;
					case "*":	result = calculator.multiply(a, b); break;
					case "/":	result = calculator.divide(a, b); break;				
				}
				
				request.setAttribute("result", result);
			}
			catch(Exception e) {
				request.setAttribute("error", e.getMessage());
			}
		}		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/calculator.jsp");
        requestDispatcher.forward(request, response);
	}

}
