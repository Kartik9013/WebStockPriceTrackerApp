<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.stocktracker.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<style>
    body {
        font-family: 'Helvetica Neue', Arial, sans-serif; /* Modern font */
        background-color: #f9f9f9; /* Light background */
        margin: 0;
        padding: 20px;
        color: #333; /* Dark text for readability */
    }
    header {
        background-color: #007bff; /* Solid blue background */
        color: #fff;
        padding: 15px 0;
        text-align: center;
        border-radius: 8px; /* Slightly rounded corners */
        box-shadow: 0 2px 5px rgba(0,0,0,0.1); /* Subtle shadow for depth */
    }
    nav {
        margin: 20px 0;
        background: #ffffff; /* White background for nav */
        padding: 10px;
        border-radius: 8px; /* Rounded corners */
        box-shadow: 0 1px 3px rgba(0,0,0,0.1); /* Subtle shadow */
    }
    nav ul {
        list-style: none;
        padding: 0;
        margin: 0;
        display: flex; /* Flexbox for horizontal layout */
        justify-content: center; /* Center the items */
    }
    
    nav ul li {
        margin: 0 15px; /* Spacing between items */
    }
    
    nav ul li a {
        text-decoration: none;
        color: #007bff; /* Link color */
        padding: 8px 15px;
        border: 1px solid transparent; /* Border for hover effect */
        border-radius: 5px; /* Rounded corners */
        transition: background 0.3s, border-color 0.3s; /* Smooth transitions */
    }
    
    nav ul li a:hover {
        background: #e9ecef; /* Light gray background on hover */
        border-color: #007bff; /* Border color on hover */
    }
    
    .welcome-message {
        font-size: 20px; /* Slightly larger font */
        font-weight: normal; /* Normal weight for professionalism */
        color: #333; /* Dark text */
        margin: 20px 0; /* Spacing above and below */
        text-align: center; /* Center the message */
    }
    
     footer {
        background-color: #007BFF; /* Blue background */
        color: white; /* White text color */
        text-align: center; /* Centered text */
        padding: 5px 0; /* Reduced padding for a smaller footer */
        position: fixed; /* Positioning */
        bottom: 0; /* Stick to the bottom */
        width: 100%; /* Full width */
        margin-top: 5px; /* Reduced margin above footer */
    }
</style>
</head>
<%
	session = request.getSession(false);
	if(session == null||session.getAttribute("user") == null){
		response.sendRedirect("login.jsp");
		return;
	}

	User user = new User();
	user = (User) session.getAttribute("user");
%>
<body>
	<header><h1>Stock Price Tracker</h1></header>
	<p><strong>Welcome, <%= user.getUsername() %>!</strong></p>
	
	<nav>
		<ul>
			<li><a href="stocklistservlet">All Stocks</a></li>
			<li><a href="mystockservlet">My Stocks</a></li>
			<li><a href="update.jsp">Change Password</a></li>
			<li><a href="delete.jsp">Delete account</a></li>
			<li><a href="logout">Log Out</a></li>
			
		</ul>
	</nav>
	
	<footer><p>&copy; Kartik. All right reserved</p></footer>
	
</body>
</html>