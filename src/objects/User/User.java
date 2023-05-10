package src.objects.User;

import java.util.UUID;

public class User {

	private String id;
	private String name;
	private int penalty;
	private String userType;

	public User(String name, String userType) {
		UUID uuid = UUID.randomUUID();
		this.id = uuid.toString();
		this.name = name;
		this.penalty = 0;
		this.userType = userType;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPenalty() {
		return penalty;
	}

	public String getUserType() {
		return userType;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}
}
