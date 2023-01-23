package main;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import pojo.DAOWeatherApp;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {

		Scanner unosGrada = new Scanner(System.in);

		String api = "https://api.openweathermap.org/data/2.5/weather?q=";
		System.out.println("Unesite grad za prikaz temperature:\n");
		String grad = unosGrada.next();
		String apiKljuc = "&appid=f558d63b1653f05ea832002d166020f9";
		String jedMerenja = "&units=metric";
		unosGrada.close();


		DAOWeatherApp dao = new DAOWeatherApp();
		dao.getCurrentWeather(api, grad, apiKljuc, jedMerenja);

	}
}
