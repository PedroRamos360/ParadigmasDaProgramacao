package src.PopulateAssets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.Asset.Asset;
import src.database.Database.Database;

public class PopulateAssets {
	public static Float getActionValue(String ticker) {
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

			String regex = "\"regularMarketPrice\"\\s*:\\s*([^,\\}]*)";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(response.toString());

			if (matcher.find()) {
				String value = matcher.group(1);
				return Float.parseFloat(value);
			}
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void populateAssets() {
		// Ações
		Asset asset = new Asset("PETR4", "Petrobras", getActionValue("PETR4"), "Ação");
		Database.addAsset(asset);
		asset = new Asset("VALE3", "Vale", getActionValue("VALE3"), "Ação");
		Database.addAsset(asset);

		// BDRs
		asset = new Asset("AAPL34", "Apple", getActionValue("AAPL34"), "BDR");
		Database.addAsset(asset);
		asset = new Asset("MSFT34", "Microsoft", getActionValue("MSFT34"), "BDR");
		Database.addAsset(asset);

		// ETFs
		asset = new Asset("BOVA11", "iShares Ibovespa", getActionValue("BOVA11"), "ETF");
		Database.addAsset(asset);
		asset = new Asset("SMAL11", "iShares Small Cap", getActionValue("SMAL11"), "ETF");
		Database.addAsset(asset);

		// FIIs
		asset = new Asset("HGLG11", "CSHG Logística", getActionValue("HGLG11"), "FII");
		Database.addAsset(asset);
		asset = new Asset("HGRE11", "CSHG Real Estate", getActionValue("HGRE11"), "FII");
		Database.addAsset(asset);
	}
}
