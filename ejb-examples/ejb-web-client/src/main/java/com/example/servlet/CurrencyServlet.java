package com.example.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.ejb.sessionbean.CurrencyRemote;
import com.example.webclient.ejb.stateless.CurrencyLocal;

/**
 * Servlet implementation class CurrencyServlet
 */
@WebServlet("/CurrencyServlet")
public class CurrencyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private CurrencyLocal currencyLocal;
	
	@EJB
	private CurrencyRemote currencyRemote;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CurrencyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void populateData(HttpServletRequest request) {
    	request.setAttribute("localCurrencies", currencyLocal.getCurrencyList());
		request.setAttribute("remoteCurrencies", currencyRemote.getCurrencyList());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		populateData(request);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/currency.jsp");
        requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		populateData(request);
		
		String localCurrency = (String) request.getParameter("localCurrency");
		String remoteCurrency = (String) request.getParameter("remoteCurrency");
		
		// set the attribute for the submitted parameter to prevent values cleared on page load 
		request.setAttribute("localCurrency", localCurrency);
		request.setAttribute("remoteCurrency", remoteCurrency);
		
		if( localCurrency == null || localCurrency.isEmpty() || localCurrency.contentEquals("???") ) {
			request.setAttribute("error", "Please select local currency!");
		}
		else if( remoteCurrency == null || remoteCurrency.isEmpty() || remoteCurrency.contentEquals("???") ) {
			request.setAttribute("error", "Please select remote currency!");
		}
		else {
			// compute the remote currency rate
			request.setAttribute("localRate", currencyLocal.getCurrencyRate(localCurrency));
			request.setAttribute("remoteRate", currencyRemote.getCurrencyRate(remoteCurrency));
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/currency.jsp");
        requestDispatcher.forward(request, response);
	}

}
