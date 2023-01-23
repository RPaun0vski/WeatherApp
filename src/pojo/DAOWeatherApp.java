package pojo;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DAOWeatherApp {
	
	public static void getCurrentWeather (String api,String grad, String apiKljuc,String jedMerenja ) throws IOException, ParseException {
		
		URL url = new URL(api+grad+apiKljuc+jedMerenja);
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

			//Parsiranje Stringa u JSON objekat
			JSONParser parse = new JSONParser();
			JSONObject data_obj = (JSONObject) parse.parse(JSONpodaci);

			//Uvezivanje trzenog objekta iz JSON dokumentacije pomocu gorekreiranog objekta
			JSONObject obj = (JSONObject) data_obj.get("main");

			//Prikaz trazenih informacija pomocu kljuca iz JSON dokumentacije
			System.out.println("Trenutna temperatura je:\t" + obj.get("temp") + " °C");
			System.out.println("Minimalna temperatura za danasnji dan je:\t" + obj.get("temp_min") + " °C");
			System.out.println("Maksimalna temperatura za danasnji dan je:\t" + obj.get("temp_max") + " °C");

		}
	}
}

