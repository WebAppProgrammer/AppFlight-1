//Based on a connection pool management Class designed by Rich Mildenberger

package data;

import java.sql.*;
import java.util.*;

public class Pool {
    private final int MAX_POOL_SIZE = 1;
	private String host;
	private String dataBase;
    private String userName;
    private String password;

    private List<Connection> connectionPool = new ArrayList<Connection>();

    public Pool(String host, String dataBase, String userName, String password) {
        this.host = host;
        this.dataBase = dataBase;
        this.userName = userName;
        this.password = password;
        while(!checkIfConnectionPoolIsFull()){
            connectionPool.add(createNewConnection());
        }
    }

    private synchronized boolean checkIfConnectionPoolIsFull(){
        return connectionPool.size()>= MAX_POOL_SIZE;
    }
    
    //Creating a connection
    private Connection createNewConnection() {
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String connString = String.format("jdbc:mysql://%s:3306/%s?user=%s&password=%s", host, dataBase, userName, password);
            connection = DriverManager.getConnection(connString);
        }catch(SQLException sqle){
            System.out.println("SQLException: "+sqle);
            return null;
        }catch(ClassNotFoundException cnfe){
            System.out.println("ClassNotFoundException: "+cnfe);
            return null;
        }
        return connection;
    }

    public synchronized Connection getConnection() {
        Connection connection = null;

        //Check if there is a connection available.
        if(connectionPool.size() > 0){
            connection = (Connection) connectionPool.get(0);
            connectionPool.remove(0);
        }else {
            connection = this.createNewConnection();
        }
        return connection;
    }

    public synchronized void returnConnection(Connection connection) {
        //Adding the connection from the client back to the pool
        if (connectionPool.size()<MAX_POOL_SIZE){
            connectionPool.add(connection);
        }else {
            try{
                connection.close();
            }
            catch(SQLException e){
                System.out.println("SQLException: "+e);
            }
        }
    }
}
