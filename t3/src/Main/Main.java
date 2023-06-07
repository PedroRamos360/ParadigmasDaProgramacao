package src.Main;

import src.database.Database.Database;

public class Main {
	public static void main(String[] args) {
		Database database = new Database();
		database.createNewDatabase();
	}
}