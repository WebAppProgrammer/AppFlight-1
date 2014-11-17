package model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Flight implements Serializable{
	private static final long serialVersionUID = 1L;
	private int flight_id;
    private Plane plane;
    @SuppressWarnings("unused")
	private String planeModel = "";
    private Operator operator;
    @SuppressWarnings("unused")
	private String operatorName = "";
    private Airport source;
    private Airport destination;
    @SuppressWarnings("unused")
	private String sourceCity = "";
    @SuppressWarnings("unused")
	private String destinCity = "";
    private Calendar departure;
    private Calendar arrival;
    @SuppressWarnings("unused")
	private String departureTime = "";
    @SuppressWarnings("unused")
	private String arrivalTime = "";
    @SuppressWarnings("unused")
	private String duration = "";
    private int first_class_reserved;
    private int business_reserved;
    private int economy_reserved;
    private int stops;
    private double cost;
    @SuppressWarnings("unused")
	private String price = "";

	public Flight(){}

	public Flight(int flight_id, Plane plane, Operator operator,
			Airport source, Airport destination, Calendar departure,
			Calendar arrival, int first_class_reserved,	int business_reserved,
			int economy_reserved, int stops, double cost) {
		super();
		this.flight_id = flight_id;
		this.plane = plane;
		this.operator = operator;
		this.source = source;
		this.destination = destination;
		this.first_class_reserved = first_class_reserved;
		this.business_reserved = business_reserved;
		this.economy_reserved = economy_reserved;
		this.departure = departure;
		this.arrival = arrival;
		this.stops = stops;
		this.cost = cost;
	}
	
	public Flight(int flight_id, Plane plane, Operator operator,
			Airport source, Airport destination, Calendar departure,
			Calendar arrival, int first_class_reserved,
			int business_reserved, int economy_reserved) {
		super();
		this.flight_id = flight_id;
		this.plane = plane;
		this.operator = operator;
		this.source = source;
		this.destination = destination;
		this.departure = departure;
		this.arrival = arrival;
		this.first_class_reserved = first_class_reserved;
		this.business_reserved = business_reserved;
		this.economy_reserved = economy_reserved;
		this.stops = 1;
		this.cost = 0.0;
	}

	public int getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public String getPlaneModel() {
		return this.plane.getPlaneId()+"";
	}

	public void setPlaneModel(String planeModel) {
		this.planeModel = planeModel;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getOperatorName() {
		return this.operator.getAirline();
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Airport getSource() {
		return source;
	}

	public void setSource(Airport source) {
		this.source = source;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public String getSourceCity() {
		String sCity = this.source.getCity() + " (" + this.source.name() + ")";
		return sCity;
	}

	public void setSourceCity(String sourceCity) {
		this.sourceCity = sourceCity;
	}

	public String getDestinCity() {
		String dCity = this.destination.getCity() + " (" + this.destination.name() + ")";
		return dCity;
	}

	public void setDestinCity(String destinCity) {
		this.destinCity = destinCity;
	}

	public Calendar getDeparture() {
		return departure;
	}

	public void setDeparture(Calendar departure) {
		this.departure = departure;
	}

	public Calendar getArrival() {
		return arrival;
	}

	public void setArrival(Calendar arrival) {
		this.arrival = arrival;
	}

	public String getDepartureTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy h:mm aa");
		return sdf.format(this.departure.getTime());
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy h:mm aa");
		return sdf.format(this.arrival.getTime());
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getFirst_class_reserved() {
		return first_class_reserved;
	}

	public void setFirst_class_reserved(int first_class_reserved) {
		this.first_class_reserved = first_class_reserved;
	}

	public int getBusiness_reserved() {
		return business_reserved;
	}

	public void setBusiness_reserved(int business_reserved) {
		this.business_reserved = business_reserved;
	}

	public int getEconomy_reserved() {
		return economy_reserved;
	}

	public void setEconomy_reserved(int economy_reserved) {
		this.economy_reserved = economy_reserved;
	}

	public int getStops() {
		return stops;
	}

	public void setStops(int stops) {
		this.stops = stops;
	}

	public double getCost() {
		//Cost will be calculated based on flying time
		long duration = this.arrival.getTimeInMillis() - this.departure.getTimeInMillis();
		double rate = 0.000035;
		//this.cost was used to suppress Eclipse warning
		double cost = this.cost*0 + duration*rate;
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getPrice() {
		String p = DecimalFormat.getCurrencyInstance().format(this.getCost());
		return p;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDuration(){
		long duration = this.arrival.getTimeInMillis() - this.departure.getTimeInMillis();
		int hours = (int)duration/(3600*1000)%24;
		int minutes = (int)duration/(60*1000)%60;
		String output = null;
		if (minutes < 10)
			output = hours + ":0" + minutes;
		else
			output = hours + ":" + minutes;
		return output;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}

	public boolean isOverbook(String cabin, int seats){
		boolean retVal = true;
		switch(FlightClass.valueOf(cabin)){
			case FirstClass:
				if(this.plane.getFirst_class_capacity() - this.first_class_reserved >= seats)
					retVal = false;
				break;
			case Business:
				if(this.plane.getBusiness_capacity() - this.business_reserved >= seats)
					retVal = false;
				break;
			case Economy:
				if(this.plane.getEconomy_capacity() - this.economy_reserved >= seats)
					retVal = false;
				break;
		}
		return retVal;
	}

	@Override
	public String toString() {
		return "Flight " + flight_id + ", " + operator + plane.getPlane_number()
				+ " from " + source + " to " + destination
				+ ", departure = " + departure.getTime()
				+ ", arrival = " + arrival.getTime()
				+ ", duration = " + this.getDuration()
				+ ", stops = " + stops
				+ ", cost = " + this.getCost();
	}
}
