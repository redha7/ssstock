package jpmorgane.examples.ssstocks;

/**
 * Common Stock has a specialized dividend
 * @author Redha
 *
 */
public class CommonStock extends Stock {

	public CommonStock(String symbol,  int lastDividend,
			Double fixedDividend, int parValue) {
		super(symbol, "Common", lastDividend, fixedDividend, parValue);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public double getDividend() {
		return getLastDividend();
	}

}
