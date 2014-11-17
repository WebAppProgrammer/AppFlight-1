package data;

import java.sql.*;

import model.User;

public class UsersDA extends DbAccess{

	public static int insertUser(User user) throws SQLException{
        Connection con;
        con = connections.getConnection();
        String query = "INSERT INTO fly_users " +
                       "(first, last, email, password, phone) " +
                       "VALUES(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, user.getFirst());
        ps.setString(2, user.getLast());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getPassword());
        ps.setString(5, user.getPhone());
        int returnValue = ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        user.setUserId(rs.getInt(1));
        ps.close();
        connections.returnConnection(con);
        return returnValue;
    }

    public static int updateUser(User user) throws SQLException{
        Connection con;
        con = connections.getConnection();
        String query = "UPDATE fly_users SET " +
                       "first = ?, " +
                       "last = ?, " +
                       "email = ?, " +
                       "password = ?, " +
                       "phone = ? " +
                       "WHERE user_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, user.getFirst());
        ps.setString(2, user.getLast());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getPassword());
        ps.setString(5, user.getPhone());
        ps.setInt(6, user.getUserId());
        int returnValue = ps.executeUpdate();
        ps.close();
        connections.returnConnection(con);
        return returnValue;
    }
    
    public static boolean isEmailTaken(String email) throws SQLException{
        Connection con = connections.getConnection();
        String query = "SELECT * FROM fly_users " +
                       "WHERE email = ?";
        PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		return rs.next();
    }

    public static boolean isLoginValid(String email, String password) throws SQLException{
        Connection con = connections.getConnection();
        String query = "SELECT * FROM fly_users " +
                       "WHERE email = ? AND password = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public static User retrieveUser(String email) throws SQLException{
        Connection con = connections.getConnection();
        String query = "SELECT * FROM fly_users " +
                       "WHERE email = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        User user = null;
        while(rs.next()){
            int userId = rs.getInt("user_id");
            String first = rs.getString("first");
            String last = rs.getString("last");
            String password = rs.getString("password");
            String phone = rs.getString("phone");
            user = new User(userId, first, last, email, password, phone);
        }
        rs.close();
        ps.close();
        connections.returnConnection(con);
        return user;
    }
    
    public static int deleteUser(User user) throws SQLException{
        Connection con;
        con = connections.getConnection();
        String query = "DELETE FROM fly_users " +
                       "WHERE email = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, user.getEmail());
        int returnValue = ps.executeUpdate();
        ps.close();
        connections.returnConnection(con);
        return returnValue;
    }

	//Code to test class
    public static void main(String[] args){
        try{
            UsersDA.setUpConnectionPool("cse.unl.edu", "wong", "wong", "booking");
            User user = new User(1, "Bob", "TestUser", "email@email.com", "password", "5551234567");            
            UsersDA.insertUser(user);
            User theUser = UsersDA.retrieveUser("email@email.com");
            System.out.println(theUser.toString());
            UsersDA.deleteUser(theUser);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
