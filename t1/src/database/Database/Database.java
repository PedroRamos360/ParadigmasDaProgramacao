package src.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import src.Main.Main;
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
					"create table book (id text, name text, authors text, year integer, edition integer, editor text, isbn text)"
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

	public Book getBookByISBN(String isbn) {
		try {
			ResultSet rs =
				this.statement.executeQuery(
						"select * from book where isbn = '" + isbn + "'"
					);
			if (!rs.next()) {
				throw new SQLException("Nenhum livro encontrado");
			}
			Book book = new Book(
				rs.getString("id"),
				rs.getString("name"),
				rs.getString("authors"),
				rs.getInt("year"),
				rs.getInt("edition"),
				rs.getString("editor"),
				rs.getString("isbn")
			);

			return book;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public ArrayList<Book> getBooksByName(String name) {
		try {
			ResultSet rs =
				this.statement.executeQuery(
						"SELECT * FROM book WHERE name LIKE '%" + name + "%'"
					);
			if (!rs.next()) {
				throw new SQLException("Nenhum livro encontrado");
			}
			ArrayList<Book> books = new ArrayList<Book>();
			do {
				Book book = new Book(
					rs.getString("id"),
					rs.getString("name"),
					rs.getString("authors"),
					rs.getInt("year"),
					rs.getInt("edition"),
					rs.getString("editor"),
					rs.getString("isbn")
				);
				books.add(book);
			} while (rs.next());

			return books;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public Book getBookByEditor(String editor) {
		try {
			ResultSet rs =
				this.statement.executeQuery(
						"SELECT * FROM book WHERE editor = '" + editor + "'"
					);
			if (!rs.next()) {
				throw new SQLException("Nenhum livro encontrado");
			}
			Book book = new Book(
				rs.getString("id"),
				rs.getString("name"),
				rs.getString("authors"),
				rs.getInt("year"),
				rs.getInt("edition"),
				rs.getString("editor"),
				rs.getString("isbn")
			);

			return book;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public Book getBookById(String id) {
		try {
			ResultSet rs =
				this.statement.executeQuery(
						"SELECT * FROM book WHERE id = '" + id + "'"
					);
			if (!rs.next()) {
				throw new SQLException("Nenhum livro encontrado");
			}
			Book book = new Book(
				rs.getString("id"),
				rs.getString("name"),
				rs.getString("authors"),
				rs.getInt("year"),
				rs.getInt("edition"),
				rs.getString("editor"),
				rs.getString("isbn")
			);

			return book;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public void editBook(Book book) {
		try {
			ResultSet rs =
				this.statement.executeQuery(
						"SELECT * FROM book WHERE isbn = '" + book.getIsbn() + "'"
					);
			if (!rs.next()) {
				throw new SQLException("Livro não encontrado");
			}
			String update = String.format(
				"update book set name = '%s', authors = '%s', year = %d, edition = %d, editor = '%s' where isbn = '%s'",
				book.getName(),
				book.getAuthors(),
				book.getYear(),
				book.getEdition(),
				book.getEditor(),
				book.getIsbn()
			);

			this.statement.executeUpdate(update);
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
				Book book = new Book(
					rs.getString("id"),
					rs.getString("name"),
					rs.getString("authors"),
					rs.getInt("year"),
					rs.getInt("edition"),
					rs.getString("editor"),
					rs.getString("isbn")
				);
				Main.printBook(book);
			} while (rs.next());
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void removeBook(String isbn) {
		try {
			this.statement.executeUpdate(
					"delete from book where isbn = '" + isbn + "'"
				);
			print("Livro removido com sucesso!\n");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void addBook(Book book) {
		try {
			String update = String.format(
				"insert into book (id, name, authors, year, edition, editor, isbn) values ('%s', '%s', '%s', %d, %d, '%s', '%s')",
				book.getId(),
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
