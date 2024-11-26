package com.stocktracker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class WatchlistServlet
 */
@WebServlet("/mystockservlet")
public class WatchlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session == null || session.getAttribute("user") == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		
		User user = (User) session.getAttribute("user");
		WatchlistDao watchlistdao = new WatchlistDao();
		Watchlist watchlist = watchlistdao.getWatchlist(user.getUsername());
		
		if(watchlist != null) {
			session.setAttribute("watchlist", watchlist);
//			request.getRequestDispatcher("mystocks.jsp").forward(request, response);
			response.sendRedirect("mystocks.jsp");
		}
		
		
	}

}
