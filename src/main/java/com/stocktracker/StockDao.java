package com.stocktracker;

import java.sql.*;

public class StockDao {
	public Stock getAllStock(){
		Stock stocks = new Stock();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// Load JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			// Establishing the connection
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_price_track_db","root","Kartik@456");
		
			// Prepared the SQL query with place holders
			String sql = "SELECT * FROM stocks";
		
			// Creating the PreparedStatement object
			pstmt = conn.prepareStatement(sql);
		
			// Execute the query
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Stock stock = new Stock();
				stock.setName(rs.getString("name"));
				stock.setSymbol(rs.getString("symbol"));
				stock.setCurrentPrice(rs.getBigDecimal("current_price"));
				
				stocks.addStockInList(stock);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {if(rs!=null) rs.close();}catch (SQLException e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();}catch (SQLException e) {e.printStackTrace();}
			try {if(conn!=null) conn.close();}catch (SQLException e) {e.printStackTrace();}
		}
		return stocks;
	}
	
	public Stock viewStockDetails(String symbol) {
		Stock stock = new Stock();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// Load JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			// Establishing the connection
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_price_track_db","root","Kartik@456");
		
			// Prepared the SQL query with place holders
			String sql = "SELECT * FROM stocks WHERE symbol = ?";
		
			// Creating the PreparedStatement object
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, symbol);
		
			// Execute the query
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				stock.setName(rs.getString("name"));
				stock.setSymbol(rs.getString("symbol"));
				stock.setOpenPrice(rs.getBigDecimal("open_price"));
				stock.setHighPrice(rs.getBigDecimal("high_price"));
				stock.setLowPrice(rs.getBigDecimal("low_price"));
				stock.setCurrentPrice(rs.getBigDecimal("current_price"));
				stock.setLatestTradingDay(rs.getDate("latest_trading_day"));
				stock.setVolume(rs.getLong("volume"));
				stock.setPreviousClose(rs.getBigDecimal("previous_close"));
				stock.setPriceChange(rs.getBigDecimal("price_change"));
				stock.setChangePercent(rs.getString("change_percent"));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {if(rs!=null) rs.close();}catch (SQLException e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();}catch (SQLException e) {e.printStackTrace();}
			try {if(conn!=null) conn.close();}catch (SQLException e) {e.printStackTrace();}
		}
		
		return stock;
		
	}
	 
}
