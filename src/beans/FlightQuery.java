package beans;

import java.io.Serializable;
import java.sql.*;

public class FlightQuery implements Serializable{
	private static final long serialVersionUID = 1L;
	private String source = "";
	private String destination = "";
	private String departure = "";
	private String arrival = "";
	private int seats = 1;
	private String flightClass = "";
	private String feedback = "";

	public FlightQuery() {
		super();
	}

	public FlightQuery(String source, String destination, String departure,
			String arrival, int seats, String flightClass, String feedback) {
		super();
		this.source = source;
		this.destination = destination;
		this.departure = departure;
		this.arrival = arrival;
		this.seats = seats;
		this.flightClass = flightClass;
		this.feedback = feedback;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDeparture() {
		return departure;
	}
	
	public Timestamp getDepartureTime() {
		return string2Timestamp(departure);
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}
	
	public Timestamp getArrivalTime() {
		return string2Timestamp(arrival);
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}
	
	public String getFeedback() {
		String retVal = this.feedback;
		this.feedback = "";
		return retVal;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	private Timestamp string2Timestamp(String input){
		//input must be in the format mm/dd/yyyy
		String[] timeSplit = input.split("/");
		int month = Integer.parseInt(timeSplit[0]);
		int day = Integer.parseInt(timeSplit[1]);
		int year = Integer.parseInt(timeSplit[2]);
		@SuppressWarnings("deprecation")
		Timestamp retVal = new Timestamp(year-1900, month-1, day, 0, 0, 0, 0);
		return retVal;
	}

	@Override
	public String toString() {
		return "FlightQuery [source=" + source + ", destination=" + destination
				+ ", departure=" + departure + ", arrival=" + arrival
				+ ", seats=" + seats + ", flightClass=" + flightClass
				+ ", feedback=" + feedback + "]";
	}
}
