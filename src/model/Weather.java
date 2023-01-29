package model;

public class Weather {
	
	//baza DAO klase, definisati atribute koji ce se uvezai sa bazom
	
	private String name;
	private double temp, feels_like, temp_min, temp_max;
	private double avg_temp;
	private int day, month;
	private String dayname;
	
	
	public Weather(String name, double temp, double feels_like, double temp_min, double temp_max) {
		super();
		this.name = name;
		this.temp = temp;
		this.feels_like = feels_like;
		this.temp_min = temp_min;
		this.temp_max = temp_max;
	}
	
	

	public Weather(String name, double avg_temp, int day, int month, String dayname) {
		super();
		this.name = name;
		this.avg_temp = avg_temp;
		this.day = day;
		this.month = month;
		this.dayname = dayname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public double getFeels_like() {
		return feels_like;
	}

	public void setFeels_like(double feels_like) {
		this.feels_like = feels_like;
	}

	public double getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(double temp_min) {
		this.temp_min = temp_min;
	}

	public double getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(double temp_max) {
		this.temp_max = temp_max;
	}

	public double getAvg_temp() {
		return avg_temp;
	}



	public void setAvg_temp(double avg_temp) {
		this.avg_temp = avg_temp;
	}



	public int getDay() {
		return day;
	}



	public void setDay(int day) {
		this.day = day;
	}



	public int getMonth() {
		return month;
	}



	public void setMonth(int month) {
		this.month = month;
	}



	public String getDayname() {
		return dayname;
	}



	public void setDayname(String dayname) {
		this.dayname = dayname;
	}



	@Override
	public String toString() {
		return "Weekday: " + dayname +"; day: " + day + "; month: " + month + "; average temperature: " + avg_temp + "\n================================\n";
	}
	
	

}
