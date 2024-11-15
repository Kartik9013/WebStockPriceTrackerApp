package com.stockupdater;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class StockPriceServlet
 */
public class StockPriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String API_KEY = "8JP1RRJ2ZY9Z24OQ";
	private static final String[] SYMBOLS = {"HDFCBANK.BSE","RELIANCE.BSE","TCS.BSE","INFY.BSE","ICICIBANK.BSE",
			"SBIN.BSE","TATAMOTORS.BSE","BAJFINANCE.BSE","AXISBANK.BSE","LT.BSE"};
	private static final String DB_URL = "jdbc:mysql://localhost:3306/stock_price_track_db?useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "Kartik@456";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			updateAllStocks();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// Method to fetch and update all stock data
	public void updateAllStocks() throws ClassNotFoundException {
		for(String symbol : SYMBOLS) {
			try {
				// Establishing connection to API URL
				String apiURL = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + API_KEY;

				@SuppressWarnings("deprecation")
				HttpURLConnection conn = (HttpURLConnection) new URL(apiURL).openConnection();
				conn.setRequestMethod("GET");
				
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuilder content = new StringBuilder();
				while((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();
				conn.disconnect();
				
				JSONObject jsonResponse = new JSONObject(content.toString());
				
				if(jsonResponse.has("Global Quote")) {
					JSONObject globalQuote = jsonResponse.getJSONObject("Global Quote");
					
					String stockName = symbol.split("\\.")[0]; // Assuming the name is the part of the symbol
					String openPrice = globalQuote.getString("02. open");
					String highprice = globalQuote.getString("03. high");
					String lowPrice = globalQuote.getString("04. low");
					String currentPrice = globalQuote.getString("05. price");
					String volume = globalQuote.getString("06. volume");
					String latestTradingDay = globalQuote.getString("07. latest trading day");
					String previousClose = globalQuote.getString("08. previous close");
					String priceChange = globalQuote.getString("09. change");
					String changePercent = globalQuote.getString("10. change percent");
					
					if((stockExist(symbol))) {
						updateStockData(stockName, symbol, openPrice, highprice, lowPrice, currentPrice, volume,
								latestTradingDay, previousClose, priceChange, changePercent);
						
					}else {
						insertStockData(stockName, symbol, openPrice, highprice, lowPrice, currentPrice, volume,
								latestTradingDay, previousClose, priceChange, changePercent);
						
					}
					
				}else {
			
					System.out.println("Global Quote not found for symbol : "+content.toString());
				}
				
			} catch (IOException | JSONException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	private void updateStockData(String stockName, String symbol, String openPrice, String highPrice,
			String lowPrice, String currentPrice, String volume, String latestTradingDay, String previousClose, String priceChange,
			String changePercent) throws ClassNotFoundException {
		
		String query = """
					   UPDATE stocks SET
					   name=?,
					   open_price=?,
					   high_price=?,
					   low_price=?,
					   current_price=?,
					   volume=?,
					   latest_trading_day=?,
					   previous_close=?,
					   price_change=?,
					   change_percent=?
					   WHERE symbol=?
				""";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			
			pstmt.setString(1, stockName);
			pstmt.setBigDecimal(2, new java.math.BigDecimal(openPrice));
			pstmt.setBigDecimal(3, new java.math.BigDecimal(highPrice));
			pstmt.setBigDecimal(4, new java.math.BigDecimal(lowPrice));
			pstmt.setBigDecimal(5, new java.math.BigDecimal(currentPrice));
			pstmt.setLong(6, Long.parseLong(volume));
			pstmt.setDate(7, Date.valueOf(latestTradingDay));
			pstmt.setBigDecimal(8, new java.math.BigDecimal(previousClose));
			pstmt.setBigDecimal(9, new java.math.BigDecimal(priceChange));
			pstmt.setString(10, changePercent);
			pstmt.setString(11, symbol);
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private boolean stockExist(String symbol) throws ClassNotFoundException {
		ResultSet rs = null;
		String query = "SELECT * FROM stocks WHERE symbol = ?";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
			PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, symbol);
			
			rs = pstmt.executeQuery();
			
			
			return rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	private void insertStockData(String stockName, String symbol, String openPrice, String highPrice,
			String lowPrice, String currentPrice, String volume, String latestTradingDay, String previousClose, String priceChange,
			String changePercent) throws ClassNotFoundException {
		String query = """
                INSERT INTO stocks (
                name,
				open_price,
				high_price,
				low_price,
				current_price,
				volume,
				latest_trading_day,
				previous_close,
				price_change,
				change_percent,
				symbol)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
			PreparedStatement pstmt = conn.prepareStatement(query)) {
			
			pstmt.setString(1, stockName);
			pstmt.setBigDecimal(2, new java.math.BigDecimal(openPrice));
			pstmt.setBigDecimal(3, new java.math.BigDecimal(highPrice));
			pstmt.setBigDecimal(4, new java.math.BigDecimal(lowPrice));
			pstmt.setBigDecimal(5, new java.math.BigDecimal(currentPrice));
			pstmt.setLong(6, Long.parseLong(volume));
			pstmt.setDate(7, Date.valueOf(latestTradingDay));
			pstmt.setBigDecimal(8, new java.math.BigDecimal(previousClose));
			pstmt.setBigDecimal(9, new java.math.BigDecimal(priceChange));
			pstmt.setString(10, changePercent);
			pstmt.setString(11, symbol);
	        
	        pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
}



