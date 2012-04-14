package org.elsys.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomTable extends Database{
	private String tableName="Room";
	public RoomTable() {
		createDatabaseConnection();
	}
	
	public RoomTable(String tableName) {
		this.tableName=tableName;
		createDatabaseConnection();
	}
	public void create() {
		try {
			stmt = conn.createStatement();
			stmt.execute("create table " + tableName
					+ "(id int primary key,name varchar(5))");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void insert(String roomNumber) {
		try {
			stmt = conn.createStatement();
			int id=(selectID().size()==0) ? 1:selectID().get(selectID().size()-1)+1;
			stmt.execute("insert into " + tableName + " values (" + ++id + ",'" + roomNumber+"')");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}

	public ArrayList<String> selectRoomNumber() {
			ArrayList<String> roomNumbers = new ArrayList<String>();
			try {
				stmt = conn.createStatement();
				ResultSet results = stmt.executeQuery("select * from " + tableName);
				while (results.next()) {
					roomNumbers.add(results.getString(2));
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
			ResultSet results = stmt.executeQuery("select * from " + tableName);
			while (results.next()) {
			while(results.next()) {
				ID.add(results.getInt(1));
			}
			}
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ID;
}


}
