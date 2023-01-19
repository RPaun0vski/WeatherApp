package dao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DAOWeatherApp {
	
	public static void API () throws IOException {
		String k="";
		StringBuffer createContent = new StringBuffer();
		//konekcija
		URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=London&appid=f558d63b1653f05ea832002d166020f9");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setReadTimeout(10000);
		con.connect();


		int response = con.getResponseCode();
		if (response!=200) {
			System.err.println("Connection not successful");
		}
		else {		
			System.out.println("Connection successful");		
			
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while ((k=inputStream.readLine())!=null) {
				createContent.append(k);
			}
			inputStream.close();
			System.out.println(createContent.append(k));
		}
	}

}
