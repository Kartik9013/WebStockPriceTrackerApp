package com.stocktracker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class DeleteServlet
 */

@WebServlet("/deleteservlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		
		if(session == null || session.getAttribute("user") == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		String password = request.getParameter("password");
		User user = new User();
		user = (User) session.getAttribute("user");
		String username = user.getUsername();
		
		UserDao ud = new UserDao();
	 	
	 	if(ud.deleteUser(password,username)) {
	 		System.out.println("Account Deleted");
	 		session.invalidate();
	 		response.sendRedirect("login.jsp");
	 	}else {
			System.out.println("Account not Deleted");
			response.sendRedirect("home.jsp");
		}
	}
}
