package data;

import java.sql.*;
import java.util.*;

import beans.FlightQuery;
import model.*;

public class FlightsDA extends DbAccess{


	public static Map<Integer, Flight> retrieveOneWayFlights(FlightQuery fq) throws SQLException{
        Airport source = Airport.valueOf(fq.getSource());
        Airport destination = Airport.valueOf(fq.getDestination());
		Map<Integer, Flight> flights = new TreeMap<Integer, Flight>();
        Connection con = connections.getConnection();
        String query =  "SELECT * FROM flights WHERE source = ? AND destination = ? AND departure BETWEEN ? AND ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, fq.getSource());
        ps.setString(2, fq.getDestination());
        ps.setTimestamp(3, fq.getDepartureTime());
        long oneDay = 1 * 24 * 60 * 60 * 1000 - 1;
        Timestamp ts = new Timestamp(fq.getDepartureTime().getTime() + oneDay);
        ps.setTimestamp(4, ts);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
        	int planeId = rs.getInt("plane");
            Plane plane = PlanesDA.retrievePlane(planeId);
            int flight_id = rs.getInt("id");
            Operator operator = Operator.valueOf(rs.getString("operator"));
            Calendar the_departure = Calendar.getInstance();
            the_departure.setTimeInMillis(rs.getTimestamp("departure").getTime());
            Calendar the_arrival = Calendar.getInstance();
            the_arrival.setTimeInMillis(rs.getTimestamp("arrival").getTime());
            int first_class_reserved = rs.getInt("first_class_reserved");
            int business_reserved = rs.getInt("business_reserved");
            int economy_reserved = rs.getInt("economy_reserved");
            Flight flight = new Flight(flight_id, plane, operator, source, destination, the_departure, the_arrival,
            						first_class_reserved, business_reserved, economy_reserved);
            if(!flight.isOverbook(fq.getFlightClass(), fq.getSeats())){
            	flights.put(flight_id, flight);
            }
        }
        rs.close();
        ps.close();
        connections.returnConnection(con);
        return flights;
    }

	//Code to test class
    public static void main(String[] args){
        try{
            FlightsDA.setUpConnectionPool("cse.unl.edu", "cse464", "wong", "booking");
            FlightQuery fq = new FlightQuery("DFW", "MIA", "1/21/2015", "1/27/2015", 2, "Business", "No feedback");
            Map<Integer, Flight> flights = FlightsDA.retrieveOneWayFlights(fq);
            for(Flight f: flights.values())
            	System.out.println(f.toString());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
