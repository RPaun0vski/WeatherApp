package main;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import dao.DAOWeatherApp;
import pojo.POJOWeatherApp;

public class Main {

	public static void main(String[] args) {

		POJOWeatherApp pojo = new POJOWeatherApp();

		try {
			pojo.getCurrentWeather();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*DAOWeatherApp dao = new DAOWeatherApp();

		try (Scanner trend = new Scanner(System.in)){
			System.out.println("Unesite grad za prikaz temperaturnog trenda:\n");
			String trend4City = trend.next();

			//dao.temperatureTrend(trend4City);
			dao.trendLength(trend4City);
		} catch (Exception e) {
			// TODO: handle exception
		}*/
	}
}

