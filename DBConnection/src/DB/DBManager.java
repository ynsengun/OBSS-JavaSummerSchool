package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
//	private static Connection con;

//	private DBManager() {
//
//	}

//	public static void initDB() {
//		
//		String url = "jdbc:mysql://localhost:3306/contact";
//		String userName = "root";
//		String password = "toor";
//
//		// stmt.executeUpdate("CREATE DATABASE contact");
//
//		try {
//			con = DriverManager.getConnection(url, userName, password);
//			createTable();
//			con.setAutoCommit(false);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	public void createTable() {
		String createTableSQL = "CREATE TABLE IF NOT EXISTS contact " + "(id INTEGER NOT NULL AUTO_INCREMENT, "
				+ " name VARCHAR(255), " + " surname VARCHAR(255), " + " phone VARCHAR(255), " + " PRIMARY KEY ( id ))";

		try (Statement stmt = DBConnection.getInstance().getConnection().createStatement();) {
			stmt.executeUpdate("DROP TABLE contact");
			stmt.executeUpdate(createTableSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Database Connection Successful");
	}

	public void commitDB() {
		try {
			DBConnection.getInstance().getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertDB(int id, String name, String surname, String phone) {
		String insertSQL = "insert into contact (name, surname, phone) values (?, ?, ?)";

		try (PreparedStatement insertPreparedStmt = DBConnection.getInstance().getConnection().prepareStatement(insertSQL);) {
			insertPreparedStmt.setString(1, name);
			insertPreparedStmt.setString(2, surname);
			insertPreparedStmt.setString(3, phone);
			insertPreparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void printDB() {
		String selectSQL = "SELECT * FROM contact WHERE name LIKE '%name%'";

		try (PreparedStatement selectPreparedStmt = DBConnection.getInstance().getConnection().prepareStatement(selectSQL);
				ResultSet resultSet = selectPreparedStmt.executeQuery();) {

			System.out.println("-----------------------------");
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2) + "  " + resultSet.getString(3)
						+ "  " + resultSet.getString(4));
			}
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateDB(String name, String phone) {
		String updateSQL = "update contact set phone = ? where name = ?";

		try (PreparedStatement updatePreparedStmt = DBConnection.getInstance().getConnection().prepareStatement(updateSQL);) {
			updatePreparedStmt.setString(1, phone);
			updatePreparedStmt.setString(2, name);
			updatePreparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteDB(int id) {
		String deleteSQL = "delete from contact where id = ?";

		try (PreparedStatement deletePreparedStmt = DBConnection.getInstance().getConnection().prepareStatement(deleteSQL);) {
			deletePreparedStmt.setInt(1, id);
			deletePreparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeDB() {
		DBConnection.getInstance().closeDB();
	}

}
