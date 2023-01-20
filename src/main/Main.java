package main;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {
	
	

	public static void main(String[] args) {
		Scanner unosGrada = new Scanner(System.in);
		
		String api = "https://api.openweathermap.org/data/2.5/weather?q=";
		System.out.println("Unesite grad za prikaz temperature:\n");
		String grad = unosGrada.next();
		String apiKlujc = "&appid=f558d63b1653f05ea832002d166020f9";
		String jedMerenja = "&units=metric";

		try {
			//konekcija
			URL url = new URL(api+grad+apiKlujc+jedMerenja);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
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

				//definisemo prazan String u koji cemo upisati JSON podatke. Upis readimo preko Skenera
				String JSONpodaci = "";
				Scanner upis = new Scanner(url.openStream());

				//Upis podataka preko Skenera
				while (upis.hasNext()) {
					JSONpodaci += upis.nextLine();
				}
				upis.close();


				//Using the JSON simple library parse the string into a json object
				JSONParser parse = new JSONParser();
				JSONObject data_obj = (JSONObject) parse.parse(JSONpodaci);

				//Get the required object from the above created object
				JSONObject obj = (JSONObject) data_obj.get("main");

				//Get the required data using its key
				System.out.println("Trenutna temperatura je:\t" + obj.get("temp") + " °C");
				System.out.println("Minimalna temperatura za danasnji dan je:\t" + obj.get("temp_min") + " °C");
				System.out.println("Maksimalna temperatura za danasnji dan je:\t" + obj.get("temp_max") + " °C");

			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
