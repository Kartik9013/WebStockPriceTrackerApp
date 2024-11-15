package com.stockupdater;

import java.util.Timer;
import java.util.TimerTask;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class StockPriceUpdater implements ServletContextListener {
	private Timer timer;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Timer timer = new Timer();
		
		// Schedule the task to run every 30 seconds
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				// call the method to fetch the stock data and update the database
				updateStockData();
				
			}
		}, 0, 30000); // run every 30 seconds
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// cancel the timer when the application is stopped or undeployed
		if(timer != null) {
			timer.cancel();
		}
	}
	
	private void updateStockData() {
		// call your servlet's stock fetching and database update logic here
		// you can refactor the code to use a separate method or class for the logic
		StockPriceServlet stockPriceServlet = new StockPriceServlet();
		try {
			stockPriceServlet.updateAllStocks(); // method to fetch and update stock data
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
