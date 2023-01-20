package pojo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import model.Main;

public class DAOWeatherApp {

	public static void API () throws IOException, ParseException {
		//String k="";
		//	StringBuilder createContent = new StringBuilder();



		//konekcija
		URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=London&appid=f558d63b1653f05ea832002d166020f9&units=metric");
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

			//Write all the JSON data into a string using a scanner
			while (upis.hasNext()) {
				JSONpodaci += upis.nextLine();
			}

			//Close the scanner
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
			



			//BufferedReader class is used to read the text from a character-based input stream. 
			//It can be used to read data line by line by readLine() method

			//InputStreamReader is a bridge from byte streams to character streams: 
			//It reads bytes and decodes them into characters using a specified charset.

			//StringBuffer class is used to create mutable (modifiable) String objects. 
			//The StringBuffer class in Java is the same as String class except it is mutable i.e. it can be changed.

			/*BufferedReader inputStream = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while ((k=inputStream.readLine())!=null) {
				createContent.append(k);
			}
			inputStream.close();*/
			//System.out.println(createContent.toString());

			/*ObjectMapper is one of the most important class available in the Jackson library. 
			 * It is used to read and write JSON data. 
			 * It is responsible for reading data from or to POJO file and to and from a JSON Tree Model.
			 */

			/*ObjectMapper mapper = new ObjectMapper();
			ObjectReader objectReader = mapper.reader().forType(new TypeReference<List<Main>>(){});*/
			//List<Main> result = objectReader.readValue(inputStream);


		}


	}

}
