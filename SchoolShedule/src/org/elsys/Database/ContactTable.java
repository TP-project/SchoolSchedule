package org.elsys.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.elsys.DatabaseConnection.Database;

public class ContactTable extends Database {
	public static final String tableName = "Contact";
	public static final String IDcolumn = "id";
	public static final String gsmColumn = "gsm";
	public static final String teacherColumn = "teacherID";

	public void create() throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("create table " + tableName + "(" + IDcolumn
				+ " int primary key," + gsmColumn + " varchar(13),"
				+ teacherColumn + " int)");
		stmt.close();
	}

	public void insert(String gsmNumber, int teacherID) throws SQLException {
		stmt = conn.createStatement();
		int id = (selectID().size() == 0) ? 1 : selectID().get(
				selectID().size() - 1) + 1;
		stmt.execute("insert into " + tableName + " values (" + id + ",'"
				+ gsmNumber + "'," + teacherID + ")");
		stmt.close();
	}

	public void delete(String gsmNumber) throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("delete from " + tableName + " where " + gsmColumn
				+ " = '" + gsmNumber + "'");
		stmt.close();
	}

	public ArrayList<String> selectGsmNumber() throws SQLException {
		ArrayList<String> numbers = new ArrayList<String>();
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select " + gsmColumn + " from "
				+ tableName);
		while (results.next()) {
			numbers.add(results.getString(1));
		}
		results.close();
		stmt.close();
		return numbers;
	}

	public ArrayList<String> selectTeacher() throws SQLException {
		ArrayList<String> teachers = new ArrayList<String>();
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select "
				+ TeacherTable.nameColumn + " from " + tableName + ", "
				+ TeacherTable.tableName + " where " + tableName + "."
				+ teacherColumn + "=" + TeacherTable.tableName + "."
				+ TeacherTable.IDcolumn);
		while (results.next()) {
			teachers.add(results.getString(1));
		}
		results.close();
		stmt.close();
		return teachers;
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

	public int getID(String gsm) throws SQLException {
		int res;
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select id from " + tableName
				+ " where " + gsmColumn + "='" + gsm + "'");
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
		ResultSet results = stmt.executeQuery("select " + gsmColumn
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
