package com.stocktracker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class stockDetailServlet
 */
@WebServlet("/stockdetailservlet")
public class stockDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session == null || session.getAttribute("user") == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		StockDao stockdao = new StockDao();
		String symbol = request.getParameter("symbol");
		
		Stock stockdetail =  stockdao.viewStockDetails(symbol);
		session.setAttribute("stockDetail", stockdetail);
		response.sendRedirect("stockDetail.jsp");
		
	}

}
