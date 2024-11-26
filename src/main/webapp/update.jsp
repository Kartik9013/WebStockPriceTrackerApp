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
            text-align: center; /* Center the Home Page button */
            margin-top: 20px; /* Add margin for spacing */
            display: inline-block;

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
        position: relative; /* Positioning */
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