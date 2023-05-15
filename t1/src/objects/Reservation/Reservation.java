package src.objects.Reservation;

import java.util.UUID;
import src.objects.Book.Book;
import src.objects.User.User;

public class Reservation {

	private String id;
	private User user;
	private Book book;

	public Reservation(String id, User user, Book book) {
		this.id = id;
		this.user = user;
		this.book = book;
	}

	public Reservation(User user, Book book) {
		this.id = UUID.randomUUID().toString();
		this.user = user;
		this.book = book;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public Book getBook() {
		return book;
	}

	public String getId() {
		return id;
	}
}
