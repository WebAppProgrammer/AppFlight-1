package model;

public enum Operator {
	AA ("American Airlines"),
	DL ("Delta Air Lines"),
	UA ("United Airlines"),
	WN ("Southwest Airlines"),
	B6 ("Jetblue Airways"),
	AS ("Alaska Airlines"),
	NK ("Spirit Airlines"),
	F9 ("Frontier Airlines");
	
	private final String airline;
	Operator(String airline){
		this.airline = airline;
	}
	
	private String airline(){
		return airline;
	}
	
	public String getAirline(){
		String airline = airline();
		return airline;
	}
}
