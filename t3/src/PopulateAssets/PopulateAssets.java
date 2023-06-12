package src.PopulateAssets;

import src.Asset.Asset;
import src.database.Database.Database;

public class PopulateAssets {
	public static void main(String[] args) {
		Database db = new Database();
		db.createNewDatabase();

		// Ações
		Asset asset = new Asset("PETR4", "Petrobras", 25.0f, "Ação");
		db.addAsset(asset);
		asset = new Asset("VALE3", "Vale", 50.0f, "Ação");
		db.addAsset(asset);

		// BDRs
		asset = new Asset("AAPL34", "Apple", 100.0f, "BDR");
		db.addAsset(asset);
		asset = new Asset("MSFT34", "Microsoft", 200.0f, "BDR");
		db.addAsset(asset);

		// ETFs
		asset = new Asset("BOVA11", "iShares Ibovespa", 150.0f, "ETF");
		db.addAsset(asset);
		asset = new Asset("SMAL11", "iShares Small Cap", 100.0f, "ETF");
		db.addAsset(asset);

		// FIIs
		asset = new Asset("HGLG11", "CSHG Logística", 200.0f, "FII");
		db.addAsset(asset);
		asset = new Asset("HGRE11", "CSHG Real Estate", 300.0f, "FII");
		db.addAsset(asset);
	}
}
