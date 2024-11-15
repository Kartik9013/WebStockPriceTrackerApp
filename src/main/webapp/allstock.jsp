<%@page import="com.stockupdater.StockPriceUpdater"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.stocktracker.*" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Stock</title>
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

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #007BFF;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        button {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
        }

        button:hover {
            background-color: #218838;
        }

        input[type="submit"] {
            background-color: #007BFF;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
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
</head>
<%
	session = request.getSession(false);
	if(session == null||session.getAttribute("user") == null){
		response.sendRedirect("login.jsp");
		return;
	}

	User user = new User();
	user = (User) session.getAttribute("user");
	
	Stock stockList = (Stock) session.getAttribute("stocklist");
	
	StockPriceUpdater spu = new StockPriceUpdater();
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

	<table border="1">
		<thead>
			<tr>
				<th>Symbol</th>
				<th>Stock Name</th>
				<th>Current Price</th>
				<th>Action</th>
			</tr>
		</thead>
		<%
			if(stockList!=null){
				for(Stock stock:stockList.getAllStockList()){
		%>
		<tbody>
		<tr>
			<td><%= stock.getSymbol() %></td>
			<td><%=  stock.getName() %></td>
			<td><%=  stock.getCurrentPrice() %></td>	
			<td>
			<a href="stockdetailservlet?symbol=<%= stock.getSymbol() %>"><button>View Details</button></a>
	         <form action="addstockservlet" method="post">
	         <input type="hidden" name="symbol" value="<%= stock.getSymbol() %>">
             <input type="submit" value="Add to Watchlist">
             </form>
			</td>
		</tr>
		<%
				}
			}else{
				%>
				<tr>
		        <td colspan="3">No stocks available</td>
		        </tr>
		<%
			}
		%>
		</tbody>
		
	</table>
	
	<footer><p>&copy; Kartik. All right reserved</p></footer>
	
</body>
</html>