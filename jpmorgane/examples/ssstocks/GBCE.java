package jpmorgane.examples.ssstocks;

import java.util.ArrayList;

/**
 * GBCE to compute all share Index
 * @author Redha
 *
 */
public class GBCE {
	private ArrayList<Stock> stocks = new ArrayList<Stock>();
/**
 * Computes all share Index
 * @return
 * @throws Exception
 */
	public double getAllShareIndex() throws Exception {
		if (stocks.size() == 0)
			throw new Exception("Null stocks");
		double product = 1.0;
		for (Stock stock : stocks)
			product *= stock.getLastPrice();	
		
		return Math.pow(product, 1.0/stocks.size());
	}
	
	public void addStock(Stock stock){
		if(stocks==null)stocks=new ArrayList<Stock>();
		stocks.add(stock);
		
	}

	public ArrayList<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(ArrayList<Stock> stocks) {
		this.stocks = stocks;
	}
	
}
