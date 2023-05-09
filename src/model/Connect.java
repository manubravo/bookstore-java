package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import security.Constant;

public class Connect {
	private Connection connect;
	public Connect() {}
	// Use only if you have database
	public Connect(String user, String password) {
		System.out.println("Connecting to database...");
		try {
			Class.forName(Constant.DRIVER);
			this.connect = DriverManager.getConnection(Constant.URL + Constant.DATABASE, user, password);
			System.out.println("Connected successfully!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// Use only if you don't have database
	public Connect(String nameDB, String user, String password) {
		System.out.println("Connecting to database...");
		try {
			Class.forName(Constant.DRIVER);
			this.connect = DriverManager.getConnection(Constant.URL, user, password);
			System.out.println("Connected successfully!");
			this.createDB(nameDB);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection on() {
		if (this.connect == null) {
			new Connect();
		}
		return this.connect;
	}
	public void off() throws SQLException {
		System.out.println("Closing connection...");
		if (this.connect != null) {
			this.connect.close();
		}
		System.out.println("Connection closed!");
	}
	private void deleteDB(String nameDB) {
		String drop = "DROP DATABASE IF EXISTS "+ nameDB +";";
		try (PreparedStatement psDrop = this.connect.prepareStatement(drop)){
			psDrop.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void createEntities() {
		System.out.println("Creating the necessary entities...");
		try (PreparedStatement psCrea = this.connect.prepareStatement(Constant.BOOK_TABLE)){
			psCrea.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void createDB(String nameDB) {
		String create = "CREATE DATABASE " + nameDB + ";";
		String use = "USE " + nameDB + ";";
		this.deleteDB(nameDB); // necessary to avoid errors
		
		try (PreparedStatement psCreate = this.connect.prepareStatement(create);
				PreparedStatement psUse = this.connect.prepareStatement(use)){
			psCreate.executeUpdate();
			psUse.executeUpdate();
			this.createEntities();
			System.out.println("Created database: " + nameDB);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}