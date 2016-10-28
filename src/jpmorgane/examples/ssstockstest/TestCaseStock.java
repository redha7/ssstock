package jpmorgane.examples.ssstockstest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import jpmorgane.examples.ssstocks.CommonStock;
import jpmorgane.examples.ssstocks.GBCE;
import jpmorgane.examples.ssstocks.PreferredStock;
import jpmorgane.examples.ssstocks.Stock;
import jpmorgane.examples.ssstocks.Trade;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Redha
 * 
 */
public class TestCaseStock {
	GBCE gbce = new GBCE();

	@Before
	public void setUp() throws Exception {
		// generates two known cases
		CommonStock cstock = new CommonStock("TEA", 0, null, 100);
		cstock.recordTrade(20, Trade.BUY, 20);
		Thread.sleep(30);
		cstock.recordTrade(30, Trade.SELL, 25);
		Thread.sleep(30);
		cstock.recordTrade(30, Trade.SELL, 26);
		Thread.sleep(30);
		cstock.recordTrade(25, Trade.SELL, 28);

		CommonStock cstock2 = new CommonStock("POP", 8, null, 100);
		cstock2.recordTrade(20, Trade.BUY, 20);
		Thread.sleep(30);
		cstock2.recordTrade(30, Trade.SELL, 25);
		Thread.sleep(30);
		cstock2.recordTrade(30, Trade.SELL, 26);
		Thread.sleep(30);
		cstock2.recordTrade(25, Trade.SELL, 30);

		// generates a preferred stock with 100 random trades
		PreferredStock pstock = new PreferredStock("POP", 8, 0.5, 100);
		for (int i = 0; i < 100; i++) {
			Thread.sleep(30);
			long quantity = (long) (80 + Math.random() * 20);
			boolean op = ((int) (Math.random() * 2)) == 1 ? true : false;
			long price = (long) (30 + Math.random() * 10); // generates random
															// prices between 30
															// and 40

			pstock.recordTrade(quantity, op, price);
		}

		gbce.addStock(cstock);
		gbce.addStock(cstock2);
		gbce.addStock(pstock);
	}

	/**
	 * Test the last price trade
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPrices() throws Exception {
		ArrayList<Stock> stocks = gbce.getStocks();

		assertTrue(stocks.get(0).getLastPrice() == 28.0);
		assertTrue(stocks.get(1).getLastPrice() == 30.0);
		assertTrue(stocks.get(2).getLastPrice() < 40
				&& stocks.get(2).getLastPrice() >= 30);

		// fail("Not yet implemented");
	}

	/**
	 * Test the formulas of dividend yield and stock price
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFormulas() throws Exception {
		ArrayList<Stock> stocks = gbce.getStocks();
		assertTrue(stocks.get(0).getDividendYeild() == 0);
		assertTrue((double) ((long) (stocks.get(1).getDividendYeild() * 100)) / 100 == 0.31);// takes
																								// two
																								// digits
																								// after
																								// .
		double value = (double) ((long) (stocks.get(2).getDividendYeild() * 100)) / 100; // takes
																							// two
																							// digits
																							// after
																							// .
		assertTrue(value >= 1 && value <= 2);

		assertTrue((double) ((long) (stocks.get(0).getStockPrice() * 100)) / 100 == 25.04);
		assertTrue((double) ((long) (stocks.get(1).getStockPrice() * 100)) / 100 == 25.52);// takes
																							// two
																							// digits
																							// after
																							// .
		double valuep = (double) ((long) (stocks.get(2).getStockPrice() * 100)) / 100; // takes
																						// two
																						// digits
																						// after
																						// .
		assertTrue(valuep >= 30 && valuep <= 40);

	}

	/**
	 * Test the all share Index
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShareIndex() throws Exception {
		double value = gbce.getAllShareIndex();
		assertTrue(value >= 20 && value <= 40);

	}

}
