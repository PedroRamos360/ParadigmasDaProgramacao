package src.objects.Loan;

import java.util.UUID;
import src.objects.Book.Book;
import src.objects.User.User;

public class Loan {

	private String id;
	private Book book;
	private User user;
	private String date;
	private String returnDate;

	public Loan(Book book, User user, String date, String returnDate) {
		UUID uuid = UUID.randomUUID();
		this.id = uuid.toString();
		this.book = book;
		this.user = user;
		this.date = date;
		this.returnDate = returnDate;
	}

	public Book getBook() {
		return book;
	}

	public String getDate() {
		return date;
	}

	public String getId() {
		return id;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public User getUser() {
		return user;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
}
