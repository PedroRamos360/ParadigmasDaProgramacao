package src.objects.Book;

import java.util.UUID;
import src.util.RandomISBNGenerator.RandomISBNGenerator;

public class Book {

	private String name;
	private String authors;
	private int year;
	private int edition;
	private String editor;
	private String isbn;
	private String id;

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Book(
		String name,
		String authors,
		int year,
		int edition,
		String editor
	) {
		UUID uuid = UUID.randomUUID();
		this.id = uuid.toString();
		this.name = name;
		this.authors = authors;
		this.year = year;
		this.edition = edition;
		this.editor = editor;
		this.isbn = RandomISBNGenerator.generateRandomISBN();
	}

	public Book(
		String id,
		String name,
		String authors,
		int year,
		int edition,
		String editor,
		String isbn
	) {
		this.id = id;
		this.name = name;
		this.authors = authors;
		this.year = year;
		this.edition = edition;
		this.editor = editor;
		this.isbn = isbn;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public String getName() {
		return this.name;
	}

	public String getEditor() {
		return this.editor;
	}

	public String getId() {
		return id;
	}

	public String getAuthors() {
		return this.authors;
	}

	public int getYear() {
		return this.year;
	}

	public int getEdition() {
		return this.edition;
	}
}
