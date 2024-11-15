package com.stocktracker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/updateservlet")
public class UpdateServlet extends HttpServlet {
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
		
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		User user = new User();
		user = (User) session.getAttribute("user");
		String username = user.getUsername();
		
		UserDao ud = new UserDao();
		
		if(ud.updatePassword(username, oldpassword, newpassword)) {
			System.out.println("Password Changed");
			response.sendRedirect("home.jsp");
		}else {
			System.out.println("Password Not Changed");
			response.sendRedirect("home.jsp");
		}
	}

}
