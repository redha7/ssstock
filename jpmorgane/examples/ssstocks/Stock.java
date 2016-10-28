package jpmorgane.examples.ssstocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Stock class, manages all stock operations
 * 
 * @author Redha
 * 
 */
public abstract class Stock {
	private String symbol;
	private String type;
	private int lastDividend;
	private Double fixedDividend;
	private int parValue;
	private ArrayList<Trade> trades = new ArrayList<Trade>();

	public Stock(String symbol, String type, int lastDividend,
			Double fixedDividend, int parValue) {
		super();
		this.symbol = symbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}

	public Stock(String symbol, String type, int lastDividend,
			Double fixedDividend, int parValue, ArrayList<Trade> trades) {
		super();
		this.symbol = symbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
		this.trades = trades;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(int lastDividend) {
		this.lastDividend = lastDividend;
	}

	public Double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public int getParValue() {
		return parValue;
	}

	public void setParValue(int parValue) {
		this.parValue = parValue;
	}

	public ArrayList<Trade> getTrades() {
		return trades;
	}

	public void setTrades(ArrayList<Trade> trades) {
		this.trades = trades;
	}
/**
 * Abstract method to be defined by sub-classes
 * 
 * @return
 */
	public abstract double getDividend();

	private void sort() {
		Collections.sort(trades, new Comparator<Trade>() {

			public int compare(Trade arg0, Trade arg1) {
				return new Long(arg0.getTimestamp()).compareTo(arg1
						.getTimestamp());
			}

		});
	}
/**
 * Computes dividend yield
 * @return
 * @throws Exception
 */
	public double getDividendYeild() throws Exception {
		return getDividend() / getStockPrice();
	}
/**
 * 
 * @return
 * @throws Exception
 */
	public double getPERatio() throws Exception {
		double dividend = getDividend();
		if (dividend == 0)
			return 0; // temporary
		return getStockPrice() / dividend;
	}
/**
 * Records a buying or selling trade
 * @param quantity
 * @param buyIndicator
 * @param price
 */
	public void recordTrade(long quantity, boolean buyIndicator, double price) {
		trades.add(new Trade(new Date().getTime(), quantity, buyIndicator,
				price));
	}
/**
 * Computes the price of the latest operation
 * @return
 * @throws Exception
 */
	public double getLastPrice() throws Exception {
		if (trades == null)
			throw new Exception("Null trades");
		int size = trades.size();
		if (size == 0)
			return 0;
		sort();
		return trades.get(size - 1).getPrice();
	}
/**
 * Compute the stock price based on the latest operations (within the last 15 min)
 * @return
 * @throws Exception
 */
	public double getStockPrice() throws Exception {
		if (trades == null)
			throw new Exception("Null trades");
		int size = trades.size();
		if (size == 0)
			return 0;
		sort();  // sorts the trades by timestamps
		long totalQuantity = 0;
		double sum = 0;
		long startTime = new Date().getTime() - 15 * 60 * 1000;
		for (int i = size - 1; i >= 0; i--) {
			Trade trade = trades.get(i);

			if (trade.getTimestamp() >= startTime) {
				long quantity = trade.getQuantity();
				sum += trade.getPrice() * quantity;
				totalQuantity += quantity;
			} else
				break; // break the loop if the current trade was not done from within 15 minutes
		}
		return sum / totalQuantity;
	}
}
