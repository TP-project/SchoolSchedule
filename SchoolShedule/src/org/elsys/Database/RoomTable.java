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
	
	public void create() {
		try {
			stmt = conn.createStatement();
			stmt.execute("create table " + tableName
					+ "(" + IDcolumn + " int primary key," + roomNumberColumn + " varchar(5))");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void insert(String roomNumber) {
		try {
			stmt = conn.createStatement();
			int id=(selectID().size()==0) ? 1:selectID().get(selectID().size()-1)+1;
			stmt.execute("insert into " + tableName + " values (" + id + ",'" + roomNumber+"')");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}

	public ArrayList<String> selectRoomNumber() {
			ArrayList<String> roomNumbers = new ArrayList<String>();
			try {
				stmt = conn.createStatement();
				ResultSet results = stmt.executeQuery("select " + roomNumberColumn + " from " + tableName);
				while (results.next()) {
					roomNumbers.add(results.getString(1));
				}
				results.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return roomNumbers;
	}

	private ArrayList<Integer> selectID() {
		ArrayList<Integer> ID = new ArrayList<Integer>();
		try {
			ResultSet results = stmt.executeQuery("select " + IDcolumn + " from " + tableName);
			while(results.next()) {
				ID.add(results.getInt(1));
			}
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ID;
}


}
