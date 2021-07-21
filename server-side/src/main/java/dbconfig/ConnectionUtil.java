package dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * As usual, this connection utility is optional. That said, I don't want to type
 * the same code again and again throughout my DAOs. This is just an abstraction.
 */
public class ConnectionUtil {

	public static Connection getConnection() throws SQLException {

		return DriverManager.getConnection(
				System.getenv("db_url"), 
				System.getenv("db_username"),
				System.getenv("db_password"));

	}

}