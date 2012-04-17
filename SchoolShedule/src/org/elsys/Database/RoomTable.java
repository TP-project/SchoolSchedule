package org.elsys.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomTable extends Database{
public static final String tableName="Room";
public static final String IDcolumn="id";
public static final String roomNumberColumn="number";
	public RoomTable() {
		createDatabaseConnection();
	}
	
	protected void finalize() throws Throwable {
		shutdownDatabase();
	}
	
	public void create() throws SQLException {
			stmt = conn.createStatement();
			stmt.execute("create table " + tableName
					+ "(" + IDcolumn + " int primary key," + roomNumberColumn + " varchar(5))");
			stmt.close();
	}

	
	public void insert(String roomNumber) throws SQLException {
			stmt = conn.createStatement();
			int id=(selectID().size()==0) ? 1:selectID().get(selectID().size()-1)+1;
			stmt.execute("insert into " + tableName + " values (" + id + ",'" + roomNumber+"')");
			stmt.close();
}
	
	public void delete(String number) throws SQLException {
			stmt = conn.createStatement();
			stmt.execute("delete from " + tableName + " where " + roomNumberColumn + " = '" + number + "'");
			stmt.close();
	}

	public ArrayList<String> selectRoomNumber() throws SQLException {
			ArrayList<String> roomNumbers = new ArrayList<String>();
				stmt = conn.createStatement();
				ResultSet results = stmt.executeQuery("select " + roomNumberColumn + " from " + tableName);
				while (results.next()) {
					roomNumbers.add(results.getString(1));
				}
				results.close();
				stmt.close();
			return roomNumbers;
	}

	private ArrayList<Integer> selectID() throws SQLException {
		ArrayList<Integer> ID = new ArrayList<Integer>();
			ResultSet results = stmt.executeQuery("select " + IDcolumn + " from " + tableName);
			while(results.next()) {
				ID.add(results.getInt(1));
			}
			results.close();
		return ID;
}

}
