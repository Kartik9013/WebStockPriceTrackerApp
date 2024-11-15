package com.stocktracker;

import java.sql.*;

public class WatchlistDao {
	public Watchlist getWatchlist(String username){
		Watchlist watchlist = new Watchlist(username);
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// Load JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			// Establishing the connection
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_price_track_db","root","Kartik@456");
		
			// Prepared the SQL query with place holders
			String sql = "SELECT s.symbol, s.name, s.current_price FROM stocks s JOIN user_stock us ON s.symbol = us.symbol WHERE us.username = ?";
		
			// Creating the PreparedStatement object
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
		
			// Execute the query
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Stock st = new Stock();
				st.setSymbol(rs.getString("symbol"));			
				watchlist.addStockinList(st);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {if(rs!=null) rs.close();}catch (SQLException e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();}catch (SQLException e) {e.printStackTrace();}
			try {if(conn!=null) conn.close();}catch (SQLException e) {e.printStackTrace();}
		}
		
		return watchlist;
	}
	
	boolean removeFromWatchlist(String username,String symbol) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			// Load JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			// Establishing the connection
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_price_track_db","root","Kartik@456");
		
			// Prepared the SQL query with place holders
			String sql = "DELETE FROM user_stock WHERE symbol = ? AND username = ?";
		
			// Creating the PreparedStatement object
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, symbol);
			pstmt.setString(2, username);
			
			// Execute the query
			row = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {if(pstmt!=null) pstmt.close();}catch (SQLException e) {e.printStackTrace();}
			try {if(conn!=null) conn.close();}catch (SQLException e) {e.printStackTrace();}
		}
		
		return row>0;
	}
	
	boolean addToWatchlist(String username,String symbol){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			// Load JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			// Establishing the connection
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_price_track_db","root","Kartik@456");
		
			// Prepared the SQL query with place holders
			
			String sql = "INSERT INTO user_stock (username,symbol) value (?,?)";
		
			// Creating the PreparedStatement object
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			pstmt.setString(2, symbol);
			
			// Execute the query
			row = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {if(pstmt!=null) pstmt.close();}catch (SQLException e) {e.printStackTrace();}
			try {if(conn!=null) conn.close();}catch (SQLException e) {e.printStackTrace();}
		}
		
		return row>0;
	}
	
	boolean isStockInWatchlist(String username,String symbol) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// Load JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			// Establishing the connection
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_price_track_db","root","Kartik@456");
		
			// Prepared the SQL query with place holders
			String sql = "SELECT * FROM user_stock WHERE username = ? AND symbol = ?";
			
			// Creating the PreparedStatement object
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			pstmt.setString(2, symbol);
			
			// Execute the query
			rs = pstmt.executeQuery();
			
			return rs.next();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {if(pstmt!=null) pstmt.close();}catch (SQLException e) {e.printStackTrace();}
			try {if(conn!=null) conn.close();}catch (SQLException e) {e.printStackTrace();}
		}
		
		return false;
	}
	
}
