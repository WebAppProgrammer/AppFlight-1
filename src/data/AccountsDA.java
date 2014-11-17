package data;

import java.sql.*;
import model.Account;

public class AccountsDA extends DbAccess{

	public static int insertAccount(Account account) throws SQLException{
        Connection con;
        con = connections.getConnection();
        String query = "INSERT INTO fly_accounts " +
                       "(holder_id, routing_number, balance) " +
                       "VALUES(?,?,?)";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, account.getHolder_id());
        ps.setInt(2, account.getRouting_number());
        ps.setDouble(3, account.getBalance());
        int returnValue = ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        account.setAccount_id(rs.getInt(1));
        ps.close();
        connections.returnConnection(con);
        return returnValue;
    }

    public static int updateAccount(Account account) throws SQLException{
        Connection con;
        con = connections.getConnection();
        String query = "UPDATE fly_accounts SET " +
                       "holder_id = ?, " +
                       "routing_number = ?, " +
                       "balance = ? " +
                       "WHERE account_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, account.getHolder_id());
        ps.setInt(2, account.getRouting_number());
        ps.setDouble(3, account.getBalance());
        ps.setInt(4, account.getAccount_id());
        int returnValue = ps.executeUpdate();
        ps.close();
        connections.returnConnection(con);
        return returnValue;
    }
    
     public static Account retrieveAccount(String holder, int account_number, int routing_number) throws SQLException{
        Connection con = connections.getConnection();
        String query = "SELECT balance FROM fly_accounts " +
                       "WHERE account_id = ? and routing_number = ? and holder_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, account_number);
        ps.setInt(2, routing_number);
        ps.setString(3, holder);
        ResultSet rs = ps.executeQuery();
        Account account = null;
        while(rs.next()){
            double balance = rs.getDouble("balance");
            account = new Account(account_number, holder, routing_number, balance);
        }
        rs.close();
        ps.close();
        connections.returnConnection(con);
        return account;
    }
    
    public static int deleteAccount(Account account) throws SQLException{
        Connection con;
        con = connections.getConnection();
        String query = "DELETE FROM fly_accounts " +
                       "WHERE account_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, account.getAccount_id());
        int returnValue = ps.executeUpdate();
        ps.close();
        connections.returnConnection(con);
        return returnValue;
    }

	//Code to test class
    public static void main(String[] args){
        try{
            AccountsDA.setUpConnectionPool("cse.unl.edu", "wong", "wong", "booking");
            Account account = new Account(1, "Bob", 123456789, 525.34);            
            AccountsDA.insertAccount(account);
            Account theAccount = AccountsDA.retrieveAccount("Bob", account.getAccount_id(), 123456789);
            System.out.println(theAccount.toString());
            AccountsDA.deleteAccount(theAccount);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
