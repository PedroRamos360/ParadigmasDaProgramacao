package src.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import src.Asset.Asset;
import src.PopulateAssets.PopulateAssets;
import src.Util.U;

public class Database extends U {
	private static void printAsset(Asset asset) {
		print("=================================");
		print("Ticker: " + asset.getTicker());
		print("Nome: " + asset.getName());
		print("Preço: " + asset.getPrice() + " reais");
		print("Tipo: " + asset.getType());
		print("Lucro: " + asset.profit + " reais");
		print("=================================");
	}

	private static void printAssetPortfolio(Asset asset, int quantity) {
		print("=================================");
		print("Ticker: " + asset.getTicker());
		print("Quantidade: " + quantity);
		print("Valor investido: " + asset.getPrice() * quantity + " reais");
		print("Lucro: " + asset.profit + " reais");
		print("=================================");
	}

	public static Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:src/database/database.db");
			return connection;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public static Statement getStatement(Connection connection) {
		try {
			Statement statement = connection.createStatement();
			return statement;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void createNewDatabase() {
		Connection connection = getConnection();
		Statement statement = getStatement(connection);
		try {
			statement.executeUpdate(
					String.format("CREATE TABLE assets (ticker TEXT, name TEXT, price FLOAT, type TEXT)"));
			statement.executeUpdate(
					"CREATE TABLE portfolioAssets (ticker TEXT, name TEXT, price FLOAT, type TEXT)");
			statement.executeUpdate(
					"CREATE TABLE balance (balance FLOAT)");
			String update = String.format(
					"insert into balance (balance) values ('%f')", 0f);
			statement.executeUpdate(update);

			PopulateAssets.populateAssets();
		} catch (SQLException e) {
			if (!e.getMessage().contains("already exists")) {
				System.err.println(e.getMessage());
			} else {
				try {
					statement.executeUpdate("DROP TABLE assets");
					statement.executeUpdate(
							String.format("CREATE TABLE assets (ticker TEXT, name TEXT, price FLOAT, type TEXT)"));
					PopulateAssets.populateAssets();
				} catch (SQLException ex) {
					print(ex);
				}
			}
		}
		closeConnection(connection);
	}

	public static float getBalance() {
		Connection connection = getConnection();
		Statement statement = getStatement(connection);
		float balance = 0;
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * from balance");
			balance = resultSet.getFloat("balance");
		} catch (SQLException e) {
			print(e.getMessage());
		}
		closeConnection(connection);
		return balance;

	}

	public static Asset getAsset(String ticker) {
		Connection connection = getConnection();
		Statement statement = getStatement(connection);
		Asset asset = null;
		try {
			String query = String.format("select * from assets where ticker = '%s'", ticker);
			ResultSet resultSet = statement.executeQuery(query);
			if (!resultSet.next())
				throw new RuntimeException("O ativo digitado não existe");
			asset = new Asset(
					resultSet.getString("ticker"),
					resultSet.getString("name"),
					resultSet.getFloat("price"),
					resultSet.getString("type"));
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		closeConnection(connection);
		return asset;
	}

	public static void listAssets() {
		Connection connection = getConnection();
		Statement statement = getStatement(connection);
		try {
			ResultSet resultSet = statement.executeQuery(String.format("select * from assets"));
			while (resultSet.next()) {
				Asset asset = new Asset(
						resultSet.getString("ticker"),
						resultSet.getString("name"),
						resultSet.getFloat("price"),
						resultSet.getString("type"));
				printAsset(asset);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		closeConnection(connection);
	}

	public static void addPortfolioAsset(String ticker) {
		Connection connection = getConnection();
		Statement statement = getStatement(connection);
		try {
			Asset asset = getAsset(ticker);
			float assetPrice = asset.getPrice();
			float balance = getBalance();
			if (assetPrice > balance)
				throw new RuntimeException(
						asset.getTicker() + " custa " + assetPrice + " reais você tem apenas " + balance + " reais");
			String updatePorfolioAssets = String.format(
					"insert into portfolioAssets (ticker, name, price, type) values ('%s', '%s', '%f', '%s')",
					asset.getTicker(),
					asset.getName(),
					assetPrice,
					asset.getType());

			String updateBalance = String.format(
					"UPDATE balance SET balance = balance - %f",
					assetPrice);
			statement.executeUpdate(updatePorfolioAssets);
			statement.executeUpdate(updateBalance);

			print("Ativo comprado com sucesso por R$ " + assetPrice + "\n");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (RuntimeException e) {
			print(e.getMessage());
		}
		closeConnection(connection);
	}

	public static void removePortfolioAsset(String ticker) {
		Connection connection = getConnection();
		Statement statement = getStatement(connection);
		try {
			Asset asset = getAsset(ticker);
			float assetPrice = asset.getPrice();

			String updatePortfolioAsset = String.format("delete from portfolioAssets where ticker = '%s'", ticker);
			String updateBalance = String.format(
					"UPDATE balance SET balance = balance + %f",
					assetPrice);
			statement.executeUpdate(updatePortfolioAsset);
			statement.executeUpdate(updateBalance);
			print("Ativo vendido com sucesso por R$ " + assetPrice + "\n");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (RuntimeException e) {
			print("O ativo digitado não existe");
		}
		closeConnection(connection);
	}

	public static void displayPortfolio() {
		Connection connection = getConnection();
		Statement statement = getStatement(connection);
		try {
			ResultSet resultSet = statement.executeQuery("select * from portfolioAssets");
			ArrayList<Asset> assets = new ArrayList<>();
			float totalInvested = 0f;
			float totalProfit = 0f;
			Map<String, Integer> countAssets = new HashMap<>();
			while (resultSet.next()) {
				String ticker = resultSet.getString("ticker");
				Asset asset = getAsset(ticker);
				countAssets.put(ticker, countAssets.getOrDefault(ticker, 0) + 1);
				totalInvested += asset.getPrice();
				asset.profit = asset.getPrice() - resultSet.getFloat("price");
				totalProfit += asset.profit;
				if (countAssets.getOrDefault(ticker, 0) == 1)
					assets.add(asset);
			}
			float balance = getBalance();
			print("+++++++++++++++++++++++++++++++++++++++++");
			print("Total não investido na conta: " + balance + " reais");
			print("Total investido na conta " + totalInvested + " reais");
			print("Total de lucro " + totalProfit + " reais");
			print("+++++++++++++++++++++++++++++++++++++++++");
			for (Asset asset : assets) {
				printAssetPortfolio(asset, countAssets.get(asset.getTicker()));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		closeConnection(connection);
	}

	public static void balanceDeposit(float value) {
		Connection connection = getConnection();
		Statement statement = getStatement(connection);
		try {
			float oldBalance = getBalance();
			String update = "UPDATE balance SET balance =" + (value + oldBalance);
			statement.executeUpdate(update);
			print("Seus " + value + " reais foram depositados com sucesso!");
		} catch (SQLException e) {
			print(e.getMessage());
		}
		closeConnection(connection);
	}

	public static void balanceWithdraw(float value) {
		Connection connection = getConnection();
		Statement statement = getStatement(connection);
		try {
			float oldBalance = getBalance();
			if (oldBalance < value)
				throw new RuntimeException("Você tem apenas " + value + " reais na sua conta!");
			String update = "UPDATE balance SET balance =" + (oldBalance - value);
			statement.executeUpdate(update);
			print("Seus " + value + " reais foram sacados com sucesso!");
		} catch (SQLException e) {
			print(e.getMessage());
		} catch (RuntimeException e) {
			print(e.getMessage());
		}
		closeConnection(connection);
	}

	public static void addAsset(Asset asset) {
		Connection connection = getConnection();
		Statement statement = getStatement(connection);
		try {
			String update = String.format(
					"insert into assets (ticker, name, price, type) values ('%s', '%s', '%f', '%s')",
					asset.getTicker(),
					asset.getName(),
					asset.getPrice(),
					asset.getType());
			statement.executeUpdate(update);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (RuntimeException e) {
			print(e.getMessage());
		}
		closeConnection(connection);
	}
}