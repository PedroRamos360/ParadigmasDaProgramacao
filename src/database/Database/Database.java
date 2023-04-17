package src.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import src.objects.Book.Book;

public class Database {

	Connection connection = null;
	Statement statement;

	private static void print(Object o) {
		System.out.print(o.toString() + "\n");
	}

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
					"create table book (name text, authors text, year integer, edition integer, editor text, isbn text)"
				);
		} catch (SQLException e) {
			if (!e.getMessage().contains("table book already exists")) {
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

	public void listBooks() {
		try {
			ResultSet rs = this.statement.executeQuery("select * from book");
			if (!rs.next()) {
				throw new SQLException("Nenhum livro encontrado");
			}
			do {
				print("========================================");
				print("Nome: " + rs.getString("name"));
				print("Autor: " + rs.getString("authors"));
				print("Ano: " + rs.getInt("year"));
				print("Edição: " + rs.getInt("edition"));
				print("Editora: " + rs.getString("editor"));
				print("ISBN: " + rs.getString("isbn"));
				print("========================================");
			} while (rs.next());
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void removeBook(String name) {
		try {
			this.statement.executeUpdate(
					"delete from book where name = '" + name + "'"
				);
			print("Livro removido com sucesso!\n");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void addBook(Book book) {
		try {
			String update = String.format(
				"insert into book (name, authors, year, edition, editor, isbn) values ('%s', '%s', %d, %d, '%s', '%s')",
				book.getName(),
				book.getAuthors(),
				book.getYear(),
				book.getEdition(),
				book.getEditor(),
				book.getIsbn()
			);
			this.statement.executeUpdate(update);

			print("Livro adicionado com sucesso!\n");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}
