package model;

public enum FlightClass {
	FirstClass ("First Class"),
	Business ("Business"),
	Economy	("Economy");
	
	private final String cabin;
	FlightClass(String cabin){
		this.cabin = cabin;
	}
	
	private String cabin(){
		return cabin;
	}
	
	public String getCabin() {
		String cabin = cabin();
		return cabin;
	}
}
