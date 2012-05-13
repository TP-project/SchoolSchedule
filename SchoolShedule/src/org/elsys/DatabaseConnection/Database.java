package org.elsys.DatabaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class Database {
	private final static String databaseName= "SchoolShefinaldueleDB";
	private static String dbURL = "jdbc:derby:/home/kosyo/" + databaseName + ";create=true;";
	protected static Connection conn = null;
	protected static Statement stmt = null;
	public void createDatabaseConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			conn = DriverManager.getConnection(dbURL);
	}

	public void shutdownDatabase() {
		try {
			if (!stmt.isClosed()) {
				stmt.close();
			}
			if (!conn.isClosed()) {
				DriverManager.getConnection(dbURL + ";shutdown=true;");
				conn.close();
			}
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}

	}
}