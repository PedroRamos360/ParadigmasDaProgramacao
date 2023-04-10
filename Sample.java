import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Sample {

	public static void main(String[] args) {
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("drop table if exists person");
			statement.executeUpdate(
				"create table person (id integer, name string, age integer, cpf string)"
			);
			statement.executeUpdate(
				"insert into person values(1, 'leo', 20, '12345678910')"
			);
			statement.executeUpdate(
				"insert into person values(2, 'yui', 30, '12345678911')"
			);
			statement.executeUpdate(
				"insert into person values(3, 'lala', 40, '12345678912')"
			);
			statement.executeUpdate(
				"insert into person values(4, 'momo', 50, '12345678913')"
			);
			statement.executeUpdate(
				"insert into person values(5, 'gigi', 60, '12345678914')"
			);
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
	}
}
