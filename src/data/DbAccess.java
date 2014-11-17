package data;

abstract class DbAccess {
	public static Pool connections;

	public static void setUpConnectionPool(String host, String dbName, String dbUser, String dbPassword){
        connections = new Pool(host, dbName, dbUser, dbPassword);
    }
}
