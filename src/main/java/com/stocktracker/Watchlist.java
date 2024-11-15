package com.stocktracker;

import java.util.ArrayList;
import java.util.List;

public class Watchlist {
	private String username;
	private List<Stock> mywatchList;
	
	public Watchlist(String username) {
		this.username = username;
		mywatchList = new ArrayList<Stock>();
	}
	
	
	public String getUsername() {
		return username;
	}
	
	public List<Stock> getMywatchList() {
		return mywatchList;
	}

	public void addStockinList(Stock st) {
		mywatchList.add(st);
	}
	
	
}
