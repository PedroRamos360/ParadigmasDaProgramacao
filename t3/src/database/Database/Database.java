package src.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import src.Asset.Asset;
import src.PopulateAssets.PopulateAssets;
import src.Util.U;

public class Database extends U {
	private static void printAsset(Asset asset) {
		print("=================================");
		print("Ticker: " + asset.getTicker());
		print("Nome: " + asset.getName());
		print("Preço: " + asset.getPrice());
		print("Tipo: " + asset.getType());
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
					"CREATE TABLE assets (ticker TEXT, name TEXT, price FLOAT, type TEXT)");
			statement.executeUpdate(
					"CREATE TABLE portfolioAssets (ticker TEXT, name TEXT, price FLOAT, type TEXT)");
			PopulateAssets.populateAssets();
		} catch (SQLException e) {
			if (!e.getMessage().contains("table assets already exists")) {
				System.err.println(e.getMessage());
			}
		}
		closeConnection(connection);
	}

	public static Asset getAsset(String ticker) {
		Connection connection = getConnection();
		Statement statement = getStatement(connection);
		Asset asset = null;
		try {
			String query = String.format("select * from assets where ticker = '%s'", ticker);
			ResultSet resultSet = statement.executeQuery(query);
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
			ResultSet resultSet = statement.executeQuery("select * from assets");
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
			String update = String.format(
					"insert into portfolioAssets (ticker, name, price, type) values ('%s', '%s', '%f', '%s')",
					asset.getTicker(),
					asset.getName(),
					asset.getPrice(),
					asset.getType());
			statement.executeUpdate(update);

			print("Ativo comprado com sucesso por R$ " + asset.getPrice() + "\n");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		closeConnection(connection);
	}

	public static void removePortfolioAsset(String ticker) {
		Connection connection = getConnection();
		Statement statement = getStatement(connection);
		try {
			String update = String.format("delete from portfolioAssets where ticker = '%s'", ticker);
			statement.executeUpdate(update);
			Asset asset = getAsset(ticker);
			print("Ativo vendido com sucesso por R$ " + asset.getPrice() + "\n");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		closeConnection(connection);
	}

	public static void displayPortfolio() {
		Connection connection = getConnection();
		Statement statement = getStatement(connection);
		try {
			ResultSet resultSet = statement.executeQuery("select * from portfolioAssets");
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
		}
		closeConnection(connection);
	}
}