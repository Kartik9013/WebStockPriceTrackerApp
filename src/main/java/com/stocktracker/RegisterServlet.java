package com.stocktracker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/registerservlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String	username = request.getParameter("username");
		String	password = request.getParameter("password");
		
	 	UserDao ud = new UserDao();
	 	
	 	if(ud.registerUser(username, password)) {
	 		System.out.println("User Registered");
	 		response.sendRedirect("login.jsp");
	 	}else {
			System.out.println("User not Registered");
			response.sendRedirect("index.jsp");
		}
		
		}
	}
