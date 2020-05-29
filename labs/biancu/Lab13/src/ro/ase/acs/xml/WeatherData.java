package ro.ase.acs.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="weatherdata")
public class WeatherData {
	@XmlElementWrapper(name="forecast")
	@XmlElement(name="time")
	public List<WeatherTime> dailyWeather;
}

class WeatherTime {
	@XmlElement(name = "symbol")
	public Description description;
	
	public Temperature temperature;
	
	@XmlElement(name = "feels_like")
	public FeelsLike feelsLike;
}

class Temperature {
	@XmlAttribute(name="day")
	public double value;
}

class FeelsLike {
	@XmlAttribute(name="day")
	public double value;
}

class Description {
	@XmlAttribute(name="name")
	public String value;
}
