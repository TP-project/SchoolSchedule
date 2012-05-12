package org.elsys.DatabaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class Database {
	private final static String databaseName= "SchoolShefinaldueleDB1";
	private static String dbURL = "jdbc:derby:/home/kosyo/" + databaseName + ";create=true;";
	protected static Connection conn = null;
	protected static Statement stmt = null;
	public void createDatabaseConnection() {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			conn = DriverManager.getConnection(dbURL);
		} catch (Exception except) {
			except.printStackTrace();
		}
	}

	public void shutdownDatabase() {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				DriverManager.getConnection(dbURL + ";shutdown=true");
				conn.close();
			}
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}

	}
}