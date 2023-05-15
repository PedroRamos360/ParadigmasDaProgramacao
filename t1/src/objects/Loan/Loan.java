package src.objects.Loan;

import java.time.LocalDate;
import java.util.UUID;
import src.objects.Book.Book;
import src.objects.User.User;

public class Loan {

	private String id;
	private Book book;
	private User user;
	private LocalDate date;
	private LocalDate returnDate;

	public Loan(Book book, User user, LocalDate date, LocalDate returnDate) {
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

	public LocalDate getDate() {
		return date;
	}

	public String getId() {
		return id;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public User getUser() {
		return user;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
}
