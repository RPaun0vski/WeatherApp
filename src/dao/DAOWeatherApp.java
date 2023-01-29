package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Weather;

public class DAOWeatherApp {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private void databaseConnection() throws ClassNotFoundException, SQLException {
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

	private double simpleAverageTemp(ArrayList<Weather> numbers) {
		double sum=0;
		if (!numbers.isEmpty()) {
			for (int i = 0; i < numbers.size(); i++) {
				sum += numbers.get(i).getTemp();
			}
		}

		return sum/numbers.size();
	}

	public int counter(ArrayList<Weather> numbers) {
		int counter=0;
		for (int j = 0; j < numbers.size(); j++) {
			if (simpleAverageTemp(numbers)<=15) {
				counter++;
			}
			else {
				counter++;
			}
		}
		return counter;
	}

	public void trendLength (String location) throws ClassNotFoundException, SQLException {

		ArrayList<Weather> list1 = new ArrayList<Weather>();

		databaseConnection();

		String b = "SELECT location, avg(temp), day(date_entry), month(date_entry), dayname(date_entry) FROM weather_app WHERE location=? GROUP BY day(date_entry);";
		preparedStatement=connect.prepareStatement(b);
		preparedStatement.setString(1, location);
		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		// listu 1 punimo srednjim vrednostima temperature u zavisnosti od broja unosa, po svakom danu
		while (resultSet.next()) {

			String location2 = resultSet.getString("location");
			double avg_temp = resultSet.getDouble("avg(temp)");
			int day = resultSet.getInt("day(date_entry)");
			int month = resultSet.getInt("month(date_entry)");
			String dayname = resultSet.getString("dayname(date_entry)");

			Weather w = new Weather(location2, avg_temp, day, month, dayname);
			list1.add(w);
			System.out.println(w);
		}
		databaseDisconnection();

		if (simpleAverageTemp(list1)<=15) {
			System.out.println("Trend hladnog vremena je trajao " + counter(list1) +  " dana, jer smo " + counter(list1) + " dana uzastopce imali hladan period ispod 15 °C");
		}
		else {
			System.out.println("Trend toplog vremena je trajao " + counter(list1) +" dana, jer smo " + counter(list1) + " dana uzastopce imali topao period iznad 15 °C");
		}
	}

	public void temperatureTrend (String location) throws ClassNotFoundException, SQLException {

		ArrayList<Weather> list = new ArrayList<Weather>();

		databaseConnection();

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

			list.add(o);
		}
		databaseDisconnection();
		if (list.size()<10) {
			System.err.println("Nedovoljan broj unosa za odredjivanje trenda");	
		}

		if (averageForLast10Entries(list)<=15) {
			System.out.println("Na osnovu posledenjih 10 provera, srednja temperatura u " + location + " je " + averageForLast10Entries(list) + " °C "
					+ "tako da smatramo da je hladno.");
		}
		else
			System.out.println("Na osnovu posledenjih 10 provera, srednja temperatura u " + location +" je " + averageForLast10Entries(list) + " °C "
					+ "tako da smatramo da je toplo.");

	}

	private void databaseDisconnection() {
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


