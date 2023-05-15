package src.util.PopulateBooksTable;

import src.database.Database.Database;
import src.objects.Book.Book;

public class PopulateBooksTable {

	public static void main(String[] args) {
		Database db = new Database();
		db.createNewDatabase();
		for (int i = 0; i < 2; i++) {
			db.addBook(
				new Book(
					"Introdução à Computação",
					"J. Glenn Brookshear",
					2011,
					10,
					"PEARSON"
				)
			);
			db.addBook(
				new Book(
					"Engenharia de Software",
					"Ian Sommerville",
					2011,
					9,
					"PEARSON"
				)
			);
			db.addBook(
				new Book(
					"Introdução à Programação com Python",
					"Nilo Ney Coutinho Menezes",
					2014,
					2,
					"NOVATEC"
				)
			);

			db.addBook(
				new Book("The Alchemist", "Paulo Coelho", 1988, 1, "HarperCollins")
			);

			db.addBook(
				new Book(
					"To Kill a Mockingbird",
					"Harper Lee",
					1960,
					1,
					"J. B. Lippincott & Co."
				)
			);

			db.addBook(
				new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 1, "Scribner")
			);
		}
	}
}
