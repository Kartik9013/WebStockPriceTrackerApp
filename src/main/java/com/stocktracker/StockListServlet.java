package com.stocktracker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class StockListServlet
 */
@WebServlet("/stocklistservlet")
public class StockListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session == null || session.getAttribute("user") == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		
		
		
		StockDao stockdao = new StockDao();
		Stock stockList = stockdao.getAllStock();
		
		
		
		if(stockList != null) {
			session.setAttribute("stocklist", stockList);
			response.sendRedirect("allstock.jsp");
		}else {
			response.sendRedirect("allstock.jsp");
		}
	}
}
