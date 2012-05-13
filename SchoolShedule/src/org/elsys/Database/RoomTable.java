package org.elsys.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.elsys.DatabaseConnection.Database;

public class RoomTable extends Database {
	public static final String tableName = "Room";
	public static final String IDcolumn = "id";
	public static final String roomNumberColumn = "number";

	public void create() throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("create table " + tableName + "(" + IDcolumn + " int,"
				+ roomNumberColumn + " varchar(5) primary key)");
		stmt.close();
	}

	public void insert(String roomNumber) throws SQLException {
		stmt = conn.createStatement();
		int id = (selectID().size() == 0) ? 1 : selectID().get(
				selectID().size() - 1) + 1;
		stmt.execute("insert into " + tableName + " values (" + id + ",'"
				+ roomNumber + "')");
		stmt.close();
	}

	public void delete(String number) throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("delete from " + tableName + " where " + roomNumberColumn
				+ " = '" + number + "'");
		stmt.close();
	}

	public ArrayList<String> selectRoomNumber() throws SQLException {
		ArrayList<String> roomNumbers = new ArrayList<String>();
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select " + roomNumberColumn
				+ " from " + tableName);
		while (results.next()) {
			roomNumbers.add(results.getString(1));
		}
		results.close();
		stmt.close();
		return roomNumbers;
	}

	private ArrayList<Integer> selectID() throws SQLException {
		ArrayList<Integer> ID = new ArrayList<Integer>();
		ResultSet results = stmt.executeQuery("select " + IDcolumn + " from "
				+ tableName);
		while (results.next()) {
			ID.add(results.getInt(1));
		}
		results.close();
		return ID;
	}

	public int getID(String room) throws SQLException {
		int res;
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select id from " + tableName
				+ " where " + roomNumberColumn + "='" + room + "'");
		if (results.next()) {
			res = results.getInt(1);
		} else
			return -1;
		results.close();
		stmt.close();
		return res;
	}

	public boolean haveString(String str) throws SQLException {
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select " + roomNumberColumn
				+ " from " + tableName);
		while (results.next()) {
			if (results.getString(1).contentEquals(str)) {
				return true;
			}
		}
		return false;

	}

	public void drop() throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("drop table " + tableName);
		stmt.close();
	}
}
