package ro.ase.acs.xml;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ProgMainXml {

	public static void main(String[] args) {
		try {
			String location = "Bucharest";
			
			//Get your API Key from https://openweathermap.org/api
			String yourApiKey = "";
			String api = String.format("http://api.openweathermap.org/data/2.5/forecast/daily?q=%s&appid=%s&mode=xml&units=metric&cnt=5&lang=en", 
					location, yourApiKey);
			URL url = new URL(api);
			URLConnection connection = url.openConnection();
			InputStream inputStream = connection.getInputStream();
			
			JAXBContext context = JAXBContext.newInstance(WeatherData.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			WeatherData weatherData = (WeatherData)unmarshaller.unmarshal(inputStream);
			for(WeatherTime t : weatherData.dailyWeather) {
				System.out.println(String.format("Temperature: %.0f* C, Feels like: %.0f* C, %s", 
						t.temperature.value, t.feelsLike.value, t.description.value));
			}
			
			Marshaller marshaller = context.createMarshaller();
			File file = new File("result.xml");
			marshaller.marshal(weatherData, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
