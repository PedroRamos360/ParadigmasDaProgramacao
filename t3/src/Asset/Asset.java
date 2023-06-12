package src.Asset;

public class Asset {
	private String ticker;
	private String name;
	private float price;
	private String type;

	public Asset(String ticker, String name, float price, String type) {
		this.ticker = ticker;
		this.name = name;
		this.price = price;
		this.type = type;
	}

	public String getTicker() {
		return this.ticker;
	}

	public String getName() {
		return this.name;
	}

	public float getPrice() {
		return this.price;
	}

	public String getType() {
		return this.type;
	}

	public String toString() {
		return String.format(
				"Ticker: %s\nName: %s\nPrice: %.2f\nType: %s\n",
				this.ticker,
				this.name,
				this.price,
				this.type);
	}
}
