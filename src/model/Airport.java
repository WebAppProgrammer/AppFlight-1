package model;

public enum Airport {
	ATL ("Atlanta, GA", "Hartsfield-Jackson"),
	ANC ("Anchorage, AK", "Ted Stevens International"),
	AUS ("Austin, TX", "Austin-Bergstrom International"),
	BWI ("Baltimore, MD", "Baltimore–Washington International"),
	BOS ("Boston, MA", "Logan International "),
	CLT ("Charlotte, NC", "Charlotte Douglas International"),
	MDW ("Chicago, IL", "Chicago Midway International"),
	ORD ("Chicago, IL", "O'Hare International"),
	CVG ("Hebron, KY ", "Cincinnati/Northern Kentucky International "),
	CLE ("Cleveland, OH", "Cleveland Hopkins International"),
	CMH ("Columbus, OH ", "Port Columbus International"),
	DFW ("DFW Airport, TX", "Dallas/Fort Worth International"),
	DEN ("Denver, CO", "Denver International"),
	DTW ("Detroit, MI", "Detroit Metropolitan Wayne County"),
	FLL ("Fort Lauderdale, FL", "Fort Lauderdale–Hollywood International"),
	RSW ("Fort Myers, FL", "Southwest Florida International Airport"),
	BDL ("Windsor Locks, CT", "Bradley International"),
	HNL ("Honolulu, HI", "Honolulu International "),
	IAH ("Houston, TX", "George Bush Intercontinental"),
	HOU ("Houston, TX", "William P. Hobby"),
	IND ("Indianapolis, IN", "Indianapolis International"),
	MCI ("Kansas City, MO", "Kansas City International"),
	LAS ("Las Vegas, NV", "McCarran International "),
	LAX ("Los Angeles, CA", "Los Angeles International"),
	MEM ("Memphis, TN", "Memphis International"),
	MIA ("Miami, FL", "Miami International"),
	MSP ("St Paul, MN", "Minneapolis–Saint Paul International"),
	BNA ("Nashville, TN", "Nashville International"),
	MSY ("Kenner, LA", "Louis Armstrong New Orleans International"),
	JFK ("New York, NY", "John F. Kennedy International"),
	LGA ("New York, NY", "LaGuardia"),
	EWR ("Newark, NJ", "Newark Liberty International"),
	OAK ("Oakland, CA", "Oakland International Airport"),
	ONT ("Ontario, CA", "Ontario International"),
	MCO ("Orlando, FL", "Orlando International"),
	PHL ("Philadelphia, PA", "Philadelphia International"),
	PHX ("Phoenix, AZ", "Phoenix Sky Harbor International"),
	PIT ("Pittsburgh, PA", "Pittsburgh International"),
	PDX ("Portland, OR", "Portland International"),
	RDU ("Morrisville, NC", "Raleigh/Durham International"),
	SMF ("Sacramento, CA", "Sacramento International"),
	SLC ("Salt Lake City, UT", "Salt Lake City International"),
	SAT ("San Antonio, TX", "San Antonio International"),
	SAN ("San Diego, CA", "San Diego International"),
	SFO ("San Francisco, CA", "San Francisco International"),
	SJC ("San Jose, CA", "Mineta San Jose International"),
	SNA ("Santa Ana, CA", "John Wayne"),
	SEA ("Seattle, WA", "Seattle-Tacoma International"),
	STL ("St Louis, MO", "St. Louis International"),
	TPA ("Tampa, FL", "Tampa International"),
	IAD ("Dulles, VA", "Dulles International"),
	DCA ("Arlington, VA", "Ronald Reagan Washington National");
	
	private final String city;
	private final String airportName;
	Airport(String city, String airportName){
		this.city = city;
		this.airportName = airportName;
	}
	
	private String city(){
		return city;
	}

	private String airportName(){
		return airportName;
	}
	
	public String getCity(){
		String city = city();
		return city;
	}
	
	public static boolean isValid(String airport){
		boolean returnVal = true;
		try{
			Airport.valueOf(airport);
		}catch(Exception e){
			returnVal = false;
		}
		return returnVal;
	}
	
	public String getAirportName(){
		String airportName = airportName();
		return airportName;
	}
}
