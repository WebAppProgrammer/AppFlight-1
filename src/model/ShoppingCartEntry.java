package model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartEntry implements Serializable {
	private static final long serialVersionUID = 1L;
	private Flight flight; 
	private List<String> seats;
	private String seatsList="";
	private String price; 
	private double total;
	private String cabin;
	private int passengers;
	
	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public ShoppingCartEntry() {
		super();
		this.seats = new ArrayList<String>();
	}

	public ShoppingCartEntry(Flight flight, List<String> seats) {
		super();
		this.seats = new ArrayList<String>();
		this.flight = flight;
		this.seats = seats;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public List<String> getSeats() {
		return seats;
	}

	public void setSeats(List<String> seats) {
		this.seats = seats;
	}

	public String getSeatsList() {
		return this.seats.toString();
	}

	public void setSeatsList(String seatsList) {
		this.seatsList = seatsList;
	}

	public String getPrice() {
		double total=this.flight.getCost()*passengers; 	
		return  DecimalFormat.getCurrencyInstance().format(total);
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public double getTotal() {
		double total=this.flight.getCost()*passengers; 	
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void removeOneSeats(){
		//this.seats.remove(seats.size()-1);
		this.passengers = this.passengers - 1;
	}

	public void addOneSeats(){
		//	this.seats.add("n/a");
		this.passengers = this.passengers + 1;
	}

	@Override
	public String toString() {
		return "ShoppingCartEntry [flight=" + flight + ", seats=" + seats
				+ ", seatsList=" + seatsList + ", price=" + price + ", total="
				+ total + ", cabin=" + cabin + ", passengers=" + passengers
				+ "]";
	}
}
