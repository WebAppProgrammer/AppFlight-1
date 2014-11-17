package data;

import java.sql.*;
import java.util.*;

import model.Booking;
import model.FlightClass;

public class BookingsDA extends DbAccess{

	public static int insertBooking(Booking booking) throws SQLException{
        Connection con;
        con = connections.getConnection();
        String query = "INSERT INTO fly_bookings " +
                       "(date_of_booking, flight_id, cabin, number_of_seats, seats, account_id, user_id, total_cost) " +
                       "VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis()));
        ps.setInt(2, booking.getFlight_id());
        ps.setString(3, booking.getCabin().name());
        ps.setInt(4, booking.getNumber_of_seats());
        ps.setString(5, booking.getSeats());
        ps.setInt(6, booking.getAccount_id());
        ps.setInt(7, booking.getUser_id());
        ps.setDouble(8, booking.getTotal_cost());
        int returnValue = ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        booking.setBooking_id(rs.getInt(1));
        ps.close();
        connections.returnConnection(con);
        return returnValue;
    }

	public static List<Booking> retrieveBookingHistory(int user_id) throws SQLException{
        List<Booking> bookings = new ArrayList<Booking>();
        Connection con = connections.getConnection();
        String query = "SELECT * FROM fly_bookings " +
        			   "WHERE user_id = ? ORDER BY date_of_booking DESC";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, user_id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int booking_id = rs.getInt("booking_id");
            Calendar date_of_booking = Calendar.getInstance(); 
            date_of_booking.setTime(rs.getTimestamp("date_of_booking"));
            int flight_id = rs.getInt("flight_id");
            FlightClass cabin = FlightClass.valueOf(rs.getString("cabin"));
            int number_of_seats = rs.getInt("number_of_seats");
            String seats = rs.getString("seats");
            int account_id = rs.getInt("account_id");
            double total_cost = rs.getDouble("total_cost");
            Booking booking = new Booking(booking_id, date_of_booking, flight_id, cabin, number_of_seats, seats, account_id, user_id, total_cost);
            bookings.add(booking);
        }
        rs.close();
        ps.close();
        connections.returnConnection(con);
        return bookings;
    }
    
    public static int deleteBooking(Booking booking) throws SQLException{
        Connection con;
        con = connections.getConnection();
        String query = "DELETE FROM fly_bookings " +
                       "WHERE booking_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, booking.getBooking_id());
        int returnValue = ps.executeUpdate();
        ps.close();
        connections.returnConnection(con);
        return returnValue;
    }

	//Code to test class
    public static void main(String[] args){
        try{
            BookingsDA.setUpConnectionPool("cse.unl.edu", "wong", "wong", "booking");
            Calendar cal = Calendar.getInstance();
            Booking booking = new Booking(1, cal, 4017, FlightClass.FirstClass, 2, "A1", 17001, 1, 299.99);
            BookingsDA.insertBooking(booking);
            List<Booking> history = BookingsDA.retrieveBookingHistory(booking.getUser_id());
            System.out.println(history.get(0).toString());
            //BookingsDA.deleteBooking(history.get(history.size()-1));
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
