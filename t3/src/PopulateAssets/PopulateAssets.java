package src.PopulateAssets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import src.Asset.Asset;
import src.database.Database.Database;

public class PopulateAssets {
	private static String getActionValue(String ticker) {
		try {
			URL url = new URL("https://brapi.dev/api/quote/" + ticker);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			connection.disconnect();

			return response.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void populateAssets() {
		// Ações
		Asset asset = new Asset("PETR4", "Petrobras", 25.0f, "Ação");
		Database.addAsset(asset);
		asset = new Asset("VALE3", "Vale", 50.0f, "Ação");
		Database.addAsset(asset);

		// BDRs
		asset = new Asset("AAPL34", "Apple", 100.0f, "BDR");
		Database.addAsset(asset);
		asset = new Asset("MSFT34", "Microsoft", 200.0f, "BDR");
		Database.addAsset(asset);

		// ETFs
		asset = new Asset("BOVA11", "iShares Ibovespa", 150.0f, "ETF");
		Database.addAsset(asset);
		asset = new Asset("SMAL11", "iShares Small Cap", 100.0f, "ETF");
		Database.addAsset(asset);

		// FIIs
		asset = new Asset("HGLG11", "CSHG Logística", 200.0f, "FII");
		Database.addAsset(asset);
		asset = new Asset("HGRE11", "CSHG Real Estate", 300.0f, "FII");
		Database.addAsset(asset);
	}
}
