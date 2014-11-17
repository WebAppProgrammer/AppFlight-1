package model;

import java.util.Calendar;

public class Ticket {
	private int number;
	private Calendar bookDate;
	private Flight flight;

	public Ticket(int number, Calendar bookDate, Flight flight) {
		super();
		this.number = number;
		this.bookDate = bookDate;
		this.flight = flight;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Calendar getBookDate() {
		return bookDate;
	}

	public void setBookDate(Calendar bookDate) {
		this.bookDate = bookDate;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	@Override
	public String toString() {
		return "Ticket number = " + number + " - " + bookDate;
	}

}
