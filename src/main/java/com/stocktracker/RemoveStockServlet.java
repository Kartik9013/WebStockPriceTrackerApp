package com.stocktracker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class removeStockTracker
 */
@WebServlet("/removestockservlet")
public class RemoveStockServlet extends HttpServlet {
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
		
		String symbol = request.getParameter("symbol");
		User user = (User) session.getAttribute("user");
		
		WatchlistDao watchlistDao = new WatchlistDao();
		
		if(watchlistDao.isStockInWatchlist(user.getUsername(), symbol)) {
			boolean isRemoved = watchlistDao.removeFromWatchlist(user.getUsername(), symbol);
			if(isRemoved) {
				request.setAttribute("message", "Stock is sucessfully removed from your watchlist");
			}else {
				request.setAttribute("error", "Stock is not removed from your watchlist");
			}
		}else {
			request.setAttribute("error", "The stock is not in your watchlist.");
		}
		
		request.getRequestDispatcher("mystockservlet").forward(request, response);	}

}
