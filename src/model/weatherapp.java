package model;

public class Weatherapp {
	
	private String mesto;
	private Main temp, feels_like;
	public Weatherapp(String mesto, Main temp, Main feels_like) {
		super();
		this.mesto = mesto;
		this.temp = temp;
		this.feels_like = feels_like;
	}
	public String getMesto() {
		return mesto;
	}
	public void setMesto(String mesto) {
		this.mesto = mesto;
	}
	public Main getTemp() {
		return temp;
	}
	public void setTemp(Main temp) {
		this.temp = temp;
	}
	public Main getFeels_like() {
		return feels_like;
	}
	public void setFeels_like(Main feels_like) {
		this.feels_like = feels_like;
	}
	@Override
	public String toString() {
		return "Weatherapp [mesto=" + mesto + ", temp=" + temp + ", feels_like=" + feels_like + "]";
	}
	
	
	
	

}
