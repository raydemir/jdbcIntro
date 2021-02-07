package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
 		//deleteDemo();
		//insertDemo();
		selectDemo();
	}
	public static void deleteDemo() throws SQLException{
		Connection connection = null;
		DbHelper helper = new DbHelper();
		PreparedStatement statement =null;
		ResultSet resultSet;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the ID of the city you want to be deleted: ");
			int deleteID = scanner.nextInt();
			System.out.println();
			connection = helper.getConnection();
			String sql = "DELETE FROM CITY WHERE ID = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1,deleteID);
			statement.executeUpdate();
			System.out.println("Registration deleted");
		} catch (SQLException exception) {
			helper.showErrorMessage(exception);
		}
		finally {
			statement.close();
			connection.close();
		}
	}
	public static void updateDemo() throws SQLException{
		Connection connection = null;
		DbHelper helper = new DbHelper();
		PreparedStatement statement =null;
		ResultSet resultSet;
		try {
			connection = helper.getConnection();
			String sql = "UPDATE CITY SET POPULATION=100000, DISTRICT='Turkey' WHERE ID = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1,4082);
			statement.executeUpdate();
			System.out.println("Registration updated");
		} catch (SQLException exception) {
			helper.showErrorMessage(exception);
		}
		finally {
			statement.close();
			connection.close();
		}
	}
	public static void insertDemo() throws SQLException{
		Connection connection = null;
		DbHelper helper = new DbHelper();
		PreparedStatement statement =null;
		ResultSet resultSet;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the city information you want to be added in order.: ((NAME, COUNTRYCODE,DISTRICT, POPULATION)) ");
			String enteredName = scanner.next();
			String enteredCountryCode = scanner.next();
			String enteredDistrict = scanner.next();
			int enteredPopulation = scanner.nextInt();

			connection = helper.getConnection();
			String sql = "INSERT INTO CITY(NAME, COUNTRYCODE,DISTRICT, POPULATION)  VALUES(?,?,?,?)";
			statement = connection.prepareStatement(sql); //("INSERT INTO CITY(NAME, COUNTRYCODE,DISTRICT, POPULATION)  VALUES('Duzce', 'TUR', 'Duzce', 50000)");
			statement.setString(1,enteredName);
			statement.setString(2,enteredCountryCode);
			statement.setString(3,enteredDistrict);
			statement.setInt   (4,enteredPopulation);
			statement.executeUpdate();
			System.out.println("Registration added");
		} catch (SQLException exception) {
			helper.showErrorMessage(exception);
		}
		finally {
			statement.close();
			connection.close();
		}
	}
	public static void selectDemo() throws SQLException {
		Connection connection = null;
		DbHelper helper = new DbHelper();
		Statement statement =null;
		ResultSet resultSet;
		try {
			connection = helper.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT CODE, NAME, CONTINENT, REGION FROM COUNTRY");
			ArrayList<Country> countries = new ArrayList<Country>();
			int i = 1;
			while (resultSet.next()){
				countries.add(new Country(
						resultSet.getString("CODE"),
						resultSet.getString("NAME"),
						resultSet.getString("CONTINENT"),
						resultSet.getString("REGION")));
				System.out.printf("%03d. %s\n", i, resultSet.getString("NAME"));
				i++;
			}
			System.out.println(countries.size());
		} catch (SQLException exception) {
			helper.showErrorMessage(exception);
		}
		finally {
			connection.close();
		}
	}
}
