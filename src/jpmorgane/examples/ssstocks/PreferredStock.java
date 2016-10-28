package jpmorgane.examples.ssstocks;

/**
 * Preferd stock, has specialized dividend
 * 
 * @author Redha
 *
 */
public class PreferredStock extends Stock{

	public PreferredStock(String symbol, int lastDividend,
			double fixedDividend, int parValue) {
		super(symbol, "Preferred", lastDividend, fixedDividend, parValue);
			}

	@Override
	public double getDividend() {
		return getFixedDividend()*getParValue();
	}

}
