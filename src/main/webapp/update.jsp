<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.stocktracker.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Password</title>
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        header {
            background-color: #007BFF;
            color: white;
            padding: 10px 0;
            text-align: center;
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

        h2 {
            color: #333;
            margin-top: 20px;
        }

        form {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            margin: auto;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }

        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        button {
            background-color: #28a745; /* Bootstrap's success color */
            color: white;
            border: none;
            padding: 10px;
            cursor: pointer;
            border-radius: 4px;
            width: 100%;
        }

        button:hover {
            background-color: #218838; /* Darker shade of success color */
        }
        
        .back {
            display: flex; /* Use flexbox to center the button */
            justify-content: center; /* Center the button horizontally */
            margin-top: 20px; /* Space above the button */
            position: absolute; /* Positioning the button */
            bottom: 70px; /* Distance from the bottom (above the footer) */
            left: 0; /* Align to the left */
            right: 0; /* Align to the right */

        }

        .back button {
            background-color: #007BFF; /* Blue color for Home Page button */
        }

        .back button:hover {
            background-color: #0056b3; /* Darker shade for hover effect */
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
if(session == null || session.getAttribute("user") == null){
	response.sendRedirect("index.jsp");
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
	
	<h2>Update Password</h2>
	<form action="updateservlet" method="post">
	<label for="oldpassword">Enter Old Password : </label>
	<input type="password" name="oldpassword" id="oldpassword" required>
	<br><br>
	<label for="newpassword">Enter New Password : </label>
	<input type="password" name="newpassword" id="newpassword" required>
	<br><br>
	<a><button>Update Password</button></a>
	</form>
	
	<div class="back">
	<a href="home.jsp"><button>Home Page</button></a>
	</div>
	
	<footer><p>&copy; Kartik. All right reserved</p></footer>
</body>
</html>