package ro.ase.acs.json;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProgMainJson {

	public static void main(String[] args) {
		try {
			String location = "Bucharest";
			
			//Get your API Key from https://openweathermap.org/api
			String yourApiKey = "";
			String api = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric&format=json&lang=en", 
					location, yourApiKey);
			URL url = new URL(api);
			URLConnection connection = url.openConnection();
			InputStream inputStream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(reader);
			
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			while((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			String jsonText = stringBuilder.toString();
			System.out.println(jsonText);
			
			JSONObject jsonObject = new JSONObject(jsonText);
			JSONObject mainObject = jsonObject.getJSONObject("main");
			long temperature = Math.round(mainObject.getDouble("temp"));
			long feelsLike = Math.round(mainObject.getDouble("feels_like"));
			
			JSONArray jsonArray = jsonObject.getJSONArray("weather");
			JSONObject weatherObject = jsonArray.getJSONObject(0);
			String description = weatherObject.getString("description");
			
			System.out.println(String.format("Temperature: %d* C, Feels like: %d* C, %s", 
					temperature, feelsLike, description));
			
			JSONObject result = new JSONObject();
			result.put("temperature", temperature);
			result.put("feels_like", feelsLike);
			result.put("description", description);
			System.out.println(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
