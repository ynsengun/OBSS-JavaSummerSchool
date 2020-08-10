package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static DBConnection dbConnection = null;
	private Connection con;
	
	static {
		dbConnection = new DBConnection();
	}
	
	static DBConnection getInstance() {
		return dbConnection;
	}
	
	Connection getConnection() {
		return this.con;
	}
	
	private DBConnection() {
		String url = "jdbc:mysql://localhost:3306/contact";
		String userName = "root";
		String password = "toor";

		// stmt.executeUpdate("CREATE DATABASE contact");

		try {
			con = DriverManager.getConnection(url, userName, password);
//			createTable();
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	void closeDB() {
		try {
			System.out.println("Not gonna close :)");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
