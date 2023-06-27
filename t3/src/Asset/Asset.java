package src.Asset;

import src.Util.U;

public class Asset extends U {
	private String ticker;
	private String name;
	private float price;
	private String type;
	public float profit;

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
}
