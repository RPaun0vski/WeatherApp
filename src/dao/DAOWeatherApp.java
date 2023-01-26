package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONObject;

import model.Weather;

public class DAOWeatherApp {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private void konekcija() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/WEATHERAPP", "root", "RaPa2022#$%");

	}

	private double averageForLast10Entries(ArrayList<Weather> numbers) {
		double sum=0;
		if (!numbers.isEmpty()) {
			for (int i = numbers.size()-1; i >= numbers.size()-10; i--) {
				sum += numbers.get(i).getTemp();
			}
		}
		else {
			System.err.println("ArrayList is empty");
		}
		return sum/10;
	}

	public void trendTemparture (String location) throws ClassNotFoundException, SQLException {

		ArrayList<Weather> lista = new ArrayList<Weather>();

		konekcija();

		String a = "SELECT * FROM weather_app WHERE location=?;";

		preparedStatement = connect.prepareStatement(a);
		preparedStatement.setString(1, location);
		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		while (resultSet.next()) {

			String location1 = resultSet.getString("location");
			double temp = resultSet.getDouble("temp");
			double feels_like = resultSet.getDouble("feels_like");
			double temp_min = resultSet.getDouble("temp_min");
			double temp_max = resultSet.getDouble("temp_max");

			Weather o = new Weather(location1, temp, feels_like, temp_min, temp_max);

			lista.add(o);
		}
		diskonekcija();
		if (lista.size()<10) {
			System.err.println("Nedovoljan broj unosa za odredjivanje trenda");	
		}

		if (averageForLast10Entries(lista)<=15) {
			System.out.println("Na osnovu posledenjih 10 provera, srednja temperatura u " + location + " je " + averageForLast10Entries(lista) + " °C "
					+ "tako da smatramo da je hladno.");
		}
		else
			System.out.println("Na osnovu posledenjih 10 provera, srednja temperatura u " + location +" je " + averageForLast10Entries(lista) + " °C "
					+ "tako da smatramo da je toplo.");

	}

	private void diskonekcija() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


