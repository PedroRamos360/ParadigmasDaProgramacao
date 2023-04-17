package src.objects.User;

import java.util.ArrayList;
import java.util.UUID;
import src.objects.Loan.Loan;

public class User {

	private String id;
	private String name;
	private float penalty;
	private String userType;
	private ArrayList<Loan> loans;

	public User(String name, String userType) {
		UUID uuid = UUID.randomUUID();
		this.id = uuid.toString();
		this.name = name;
		this.penalty = 0;
		this.userType = userType;
		this.loans = new ArrayList<Loan>();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getPenalty() {
		return penalty;
	}

	public String getUserType() {
		return userType;
	}

	public void setPenalty(float penalty) {
		this.penalty = penalty;
	}
}
