package model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Booking implements Serializable{
	private static final long serialVersionUID = 1L;
	private int booking_id;
	private Calendar date_of_booking;
	@SuppressWarnings("unused")
	private String bookingTime = "";
	private int flight_id;
	private FlightClass cabin;
	@SuppressWarnings("unused")
	private String cabinName = "";
	private int number_of_seats;
	private String seats;
	private int account_id;
	private int user_id;
	private double total_cost;
	@SuppressWarnings("unused")
	private String total = "";
	private String passenger = "";
	private String dob = "";
	private String gender = "";

	public Booking() {
		super();
	}

	public Booking(int booking_id, Calendar date_of_booking, int flight_id,
			FlightClass cabin, int number_of_seats, String seats,
			int account_id, int user_id, double total_cost) {
		super();
		this.booking_id = booking_id;
		this.date_of_booking = date_of_booking;
		this.flight_id = flight_id;
		this.cabin = cabin;
		this.number_of_seats = number_of_seats;
		this.seats = seats;
		this.account_id = account_id;
		this.user_id = user_id;
		this.total_cost = total_cost;
	}

	public int getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}

	public Calendar getDate_of_booking() {
		return date_of_booking;
	}

	public void setDate_of_booking(Calendar date_of_booking) {
		this.date_of_booking = date_of_booking;
	}

	public String getBookingTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy h:mm aa");
		return sdf.format(this.date_of_booking.getTime());
	}

	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}

	public int getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}

	public FlightClass getCabin() {
		return cabin;
	}

	public void setCabin(FlightClass cabin) {
		this.cabin = cabin;
	}
	
	public String getCabinName(){
		return this.cabin.getCabin();
	}
	
	public void setCabinName(String cabinName){
		this.cabinName = cabinName;
	}

	public int getNumber_of_seats() {
		return number_of_seats;
	}

	public void setNumber_of_seats(int number_of_seats) {
		this.number_of_seats = number_of_seats;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public double getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(double total_cost) {
		this.total_cost = total_cost;
	}

	public String getTotal() {
		String t = DecimalFormat.getCurrencyInstance().format(this.total_cost);
		return t;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getPassenger() {
		return passenger;
	}

	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Booking(int booking_id, Calendar date_of_booking, int flight_id,
			FlightClass cabin, String cabinName, int number_of_seats,
			String seats, int account_id, int user_id, double total_cost,
			String total, String passenger, String dob, String gender) {
		super();
		this.booking_id = booking_id;
		this.date_of_booking = date_of_booking;
		this.flight_id = flight_id;
		this.cabin = cabin;
		this.cabinName = cabinName;
		this.number_of_seats = number_of_seats;
		this.seats = seats;
		this.account_id = account_id;
		this.user_id = user_id;
		this.total_cost = total_cost;
		this.total = total;
		this.passenger = passenger;
		this.dob = dob;
		this.gender = gender;
	}
}
