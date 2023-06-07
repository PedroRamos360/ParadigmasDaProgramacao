package src.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	Connection connection = null;
	Statement statement;

	public Database() {
		try {
			this.connection =
				DriverManager.getConnection("jdbc:sqlite:src/database/database.db");
			this.statement = connection.createStatement();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void createNewDatabase() {
		try {
			this.statement.executeUpdate(
					"create table assets (ticker text, name text, price float, type text)"
				);
		} catch (SQLException e) {
			if (!e.getMessage().contains("table assets already exists")) {
				System.err.println(e.getMessage());
			}
		}
	}

	public void closeConnection() {
		try {
			if (connection != null) connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}