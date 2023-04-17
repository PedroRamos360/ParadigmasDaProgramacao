package src.objects.Book;

import src.objects.RandomISBNGenerator.RandomISBNGenerator;

public class Book {

	private String name;
	private String authors;
	private int year;
	private int edition;
	private String editor;
	private String isbn;

	public Book(
		String name,
		String authors,
		int year,
		int edition,
		String editor
	) {
		this.name = name;
		this.authors = authors;
		this.year = year;
		this.edition = edition;
		this.editor = editor;
		this.isbn = RandomISBNGenerator.generateRandomISBN();
	}

	public String getIsbn() {
		return isbn;
	}

	public String getName() {
		return name;
	}

	public String getEditor() {
		return editor;
	}

	public String getAuthors() {
		return authors;
	}

	public int getYear() {
		return year;
	}

	public int getEdition() {
		return edition;
	}
}
