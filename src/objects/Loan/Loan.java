package src.objects.Loan;

import java.util.UUID;

public class Loan {

	private String id;
	private String bookId;
	private String userId;
	private String date;
	private String returnDate;

	public Loan(String bookId, String userId, String date, String returnDate) {
		UUID uuid = UUID.randomUUID();
		this.id = uuid.toString();
		this.bookId = bookId;
		this.userId = userId;
		this.date = date;
		this.returnDate = returnDate;
	}

	public String getBookId() {
		return bookId;
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

	public String getUserId() {
		return userId;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
}
