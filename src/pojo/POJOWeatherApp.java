package pojo;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class POJOWeatherApp {

	private URL url=null;
	private HttpURLConnection con=null;
	private Connection connect=null;
	private PreparedStatement preparedStatement = null;

	public void getCurrentWeather () throws IOException, ParseException {
		Scanner cityInput = new Scanner(System.in);
		System.out.println("Unesite grad za prikaz temperature:\n");
		String city = cityInput.next();
		cityInput.close();

		String api = "https://api.openweathermap.org/data/2.5/weather?q=";
		String apiKey = "&appid=f558d63b1653f05ea832002d166020f9";
		String uom = "&units=metric";

		url = new URL(api+city+apiKey+uom);
		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setReadTimeout(10000);
		con.connect();

		//test konekcije
		int response = con.getResponseCode();
		if (response!=200) {
			System.err.println("Connection not successful");
		}
		else {		
			System.out.println("Connection successful");	
			System.out.println();

			//definisemo prazan String u koji cemo upisati JSON podatke. Upis readimo preko Skenera
			String JSONpodaci = "";
			Scanner input = new Scanner(url.openStream());

			//Upis podataka preko Skenera
			while (input.hasNext()) {
				JSONpodaci += input.nextLine();
			}
			input.close();

			//Parsiranje Stringa u JSON objekat
			JSONParser parse = new JSONParser();
			JSONObject data_obj = (JSONObject) parse.parse(JSONpodaci);
			//moze i            = (JSONObject) parse(new FileReader("lokacija.json"));

			//Uvezivanje trzenog objekta iz JSON dokumentacije pomocu gorekreiranog objekta

			JSONObject obj = (JSONObject) data_obj.get("main");

			//Prikaz trazenih informacija pomocu kljuca iz JSON dokumentacije
			//System.out.println(grad);
			System.out.println("Trenutna temperatura je:\t" + obj.get("temp") + " °C");
			System.out.println("Subjektivni osecaj je:\t" + obj.get("feels_like") + " °C");
			System.out.println("Minimalna temperatura za danasnji dan je:\t" + obj.get("temp_min") + " °C");
			System.out.println("Maksimalna temperatura za danasnji dan je:\t" + obj.get("temp_max") + " °C");
			System.out.println();

			//Object obj1 = parse.parse(new FileReader("D://Program learning/main.json"));
			JSONObject jsonObject = (JSONObject ) data_obj;
			JSONObject itemize = (JSONObject) jsonObject.get("main");

			String location=city;
			double temp=(double) itemize.get("temp");
			double feels_like=(double) itemize.get("feels_like");
			double temp_min=(double) itemize.get("temp_min");
			double temp_max=(double) itemize.get("temp_max");

			//konekcija sa DB
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/WEATHERAPP", "root", "RaPa2022#$%");
				System.out.println("Connection to database established...");
			} catch (Exception e) {
				e.printStackTrace();
			} 

			//pozivanje upita
			try {
				String s = "INSERT INTO weather_app (location, temp, feels_like, temp_min, temp_max) VALUES (?, ?, ?, ?, ?);";
				preparedStatement = connect.prepareStatement(s);
			} catch (Exception e) {
				e.printStackTrace();
			} 

			// za id se ne radi jer je auto_inkrement
			try {
				preparedStatement.setString(1, location);
				preparedStatement.setDouble(2, temp);
				preparedStatement.setDouble(3, feels_like);
				preparedStatement.setDouble(4, temp_min);
				preparedStatement.setDouble(5, temp_max);
				preparedStatement.executeUpdate();
				System.out.println("Input into database completed");
				System.out.println();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			} 

			//zatvaranje konekcije
			try {
				if (connect != null) {
					connect.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}



