package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBManager {
	private static Connection con;
	
	private DBManager() { }
	
	static {
		System.out.println("In static");
		String url = "jdbc:mysql://localhost:3306/contact";
		String userName = "root";
		String password = "toor";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
			createTable();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void createTable() {
		String createTableSQL = "CREATE TABLE IF NOT EXISTS contact " + "(id INTEGER NOT NULL AUTO_INCREMENT, "
				+ " name VARCHAR(255), " + " phone VARCHAR(255), " + " PRIMARY KEY ( id ))";

		try (Statement stmt = con.createStatement();) {
			stmt.executeUpdate("DROP TABLE contact");
			stmt.executeUpdate(createTableSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Database Connection Successful");
	}
	

	public static void insertDB(Contact contact) {
		String insertSQL = "insert into contact (name, phone) values (?, ?)";

		try (PreparedStatement insertPreparedStmt = con.prepareStatement(insertSQL);) {
			insertPreparedStmt.setString(1, contact.getName());
			insertPreparedStmt.setString(2, contact.getPhone());
			insertPreparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateDB(Contact contact) {
		String updateSQL = "update contact set phone = ? where name = ?";

		try (PreparedStatement updatePreparedStmt = con.prepareStatement(updateSQL);) {
			updatePreparedStmt.setString(1, contact.getPhone());
			updatePreparedStmt.setString(2, contact.getName());
			updatePreparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Contact searchDB( String name) {
		String selectSQL = "SELECT * FROM contact WHERE name LIKE ?";
		ResultSet resultSet = null;
		Contact contact = null;
		
		try (PreparedStatement selectPreparedStmt = con.prepareStatement(selectSQL);) {
			
			selectPreparedStmt.setString(1, name);
			resultSet = selectPreparedStmt.executeQuery();
			if(resultSet.next()) {
				contact = new Contact(resultSet.getString(2), resultSet.getString(3));
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return contact;
	}
	
	public static ArrayList<Contact> getAll() {
		String selectSQL = "SELECT * FROM contact";
		ArrayList<Contact> contacts = new ArrayList<>();
		
		try (PreparedStatement selectPreparedStmt = con.prepareStatement(selectSQL);
				ResultSet resultSet = selectPreparedStmt.executeQuery();) {
			
			while(resultSet.next()){
				Contact contact = new Contact(resultSet.getString(2), resultSet.getString(3));
				contacts.add(contact);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return contacts;
	}
}
