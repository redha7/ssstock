package jpmorgane.examples.ssstocks;

/**
 * Trade class for recording single trades
 * 
 * @author Redha
 *
 */
public class Trade {
private long timestamp;
private long quantity;
private boolean buyIndicator; // true=buy  false=sell
private double price;

public static final boolean BUY=true;
public static final boolean SELL=false;

public long getTimestamp() {
	return timestamp;
}
public void setTimestamp(long timestamp) {
	this.timestamp = timestamp;
}
public long getQuantity() {
	return quantity;
}
public void setQuantity(long quantity) {
	this.quantity = quantity;
}
public boolean isBuyIndicator() {
	return buyIndicator;
}
public void setBuyIndicator(boolean buyIndicator) {
	this.buyIndicator = buyIndicator;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}

	public Trade(long timestamp, long quantity, boolean buyIndicator,
			double price) {
	super();
	this.timestamp = timestamp;
	this.quantity = quantity;
	this.buyIndicator = buyIndicator;
	this.price = price;
}
public String toString() {
	return "Trade [timestamp=" + timestamp + ", quantity=" + quantity
			+ ", buyIndicator=" + buyIndicator + ", price=" + price + "]";
}

}
