package model;

public class Weatherapp {
	
	private String mesto;

	public Weatherapp(String mesto) {
		super();
		this.mesto = mesto;
	}

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	@Override
	public String toString() {
		return "Weatherapp [mesto=" + mesto + "]";
	}
	
	
	
	
	
	

}
