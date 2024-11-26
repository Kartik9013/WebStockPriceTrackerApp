package com.stocktracker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class AddStockServlet
 */
@WebServlet("/addstockservlet")
public class AddStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session == null || session.getAttribute("user") == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		
		WatchlistDao watchlistdao = new WatchlistDao();
		String symbol = request.getParameter("symbol");
		User user = (User) session.getAttribute("user");
		
		if(!watchlistdao.isStockInWatchlist(user.getUsername(), symbol)) {
			watchlistdao.addToWatchlist(user.getUsername(), symbol);
			response.sendRedirect("allstock.jsp");
		}else {
			response.sendRedirect("allstock.jsp");
		}
	}

}
