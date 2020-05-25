package ro.ase.acs.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteProgMain {

	public static void main(String[] args) {
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = 
					DriverManager.getConnection("jdbc:sqlite:database.db");
			connection.setAutoCommit(false);
			createTable(connection);
			insertValues(connection);
			selectData(connection);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void createTable(Connection connection) {
		String sqlDrop = "DROP TABLE IF EXISTS employees";
		String sqlCreate = "CREATE TABLE employees(id INTEGER PRIMARY KEY, " +
				"name TEXT, birthdate LONG, address TEXT, salary REAL)";
		
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sqlDrop);
			statement.executeUpdate(sqlCreate);
			statement.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertValues(Connection connection) {
		String sqlInsert = "INSERT INTO employees VALUES(1, 'Ionel Popescu', 1589874134752, " +
								"'Stefan cel Mare nr 20', 2000)";
		
		String sqlInsertWithParams = "INSERT INTO employees(name, birthdate, address, salary) " +
										"VALUES(?, ?, ?, ?)";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sqlInsert);
			statement.close();
			connection.commit();
			
			PreparedStatement preparedStatement =
					connection.prepareStatement(sqlInsertWithParams);
			preparedStatement.setString(1, "Gigel Ionescu");
			preparedStatement.setLong(2, Date.valueOf("1995-05-17").getTime());
			preparedStatement.setString(3, "Mihai Bravu nr 15");
			preparedStatement.setDouble(4, 4000);
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void selectData(Connection connection) {
		String sqlSelect = "SELECT * FROM employees";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sqlSelect);
			while(rs.next()) {
				int id = rs.getInt("id");
				System.out.println("id: " + id);
				String name = rs.getString("name");
				System.out.println("name: " + name);
				long birthDate = rs.getLong("birthdate");
				System.out.println("birthdate: " + new Date(birthDate));
				String address = rs.getString("address");
				System.out.println("address: " + address);
				double salary = rs.getDouble("salary");
				System.out.println("salary: " + salary);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
