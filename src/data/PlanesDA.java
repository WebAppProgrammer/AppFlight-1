package data;

import java.sql.*;
import model.*;

public class PlanesDA extends DbAccess{

	public static Plane retrievePlane(int planeId) throws SQLException{
        Connection con = connections.getConnection();
        String query =  "SELECT * FROM planes WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, planeId);
        ResultSet rs = ps.executeQuery();
        Plane plane = null;
        while(rs.next()){
        	int plane_number = rs.getInt("plane_number");
            int first_class_capacity = rs.getInt("first_class_capacity");
            int business_capacity = rs.getInt("business_capacity");
            int economy_capacity = rs.getInt("economy_capacity");
            plane = new Plane(planeId, plane_number,
            		first_class_capacity, business_capacity, economy_capacity);
        }
        rs.close();
        ps.close();
        connections.returnConnection(con);        
        return plane;
    }

	//Code to test class
    public static void main(String[] args){
        try{
            PlanesDA.setUpConnectionPool("cse.unl.edu", "cse464", "wong", "booking");
            Plane plane = PlanesDA.retrievePlane(1);
            System.out.println(plane.toString());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
