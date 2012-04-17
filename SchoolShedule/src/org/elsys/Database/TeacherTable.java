package org.elsys.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherTable extends Database {
	public static final String tableName = "Teacher";
	public static final String IDcolumn = "id";
	public static final String shortNameColumn = "short_name";
	public static final String nameColumn = "name";

	public TeacherTable() {
		createDatabaseConnection();
	}
	
	protected void finalize() throws Throwable {
		shutdownDatabase();
	}

	public void create() throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("create table " + tableName + "(" + IDcolumn
				+ " int primary key," + nameColumn + " varchar(12),"
				+ shortNameColumn + " varchar(5))");
		stmt.close();
	}

	public void insert(String name, String shortName) throws SQLException {
		stmt = conn.createStatement();
		int id = (selectID().size() == 0) ? 1 : selectID().get(
				selectID().size() - 1) + 1;
		stmt.execute("insert into " + tableName + " values (" + id + ",'"
				+ name + "','" + shortName + "')");
		stmt.close();
	}

	public void delete(String name) throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("delete from " + tableName + " where " + nameColumn
				+ " = '" + name + "'");
		stmt.close();
	}

	public ArrayList<String> selectName() throws SQLException {
		ArrayList<String> teacherNames = new ArrayList<String>();
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select " + nameColumn + " from "
				+ tableName);
		while (results.next()) {
			teacherNames.add(results.getString(1));
		}
		results.close();
		stmt.close();
		return teacherNames;
	}

	public ArrayList<String> selectShortName() throws SQLException {
		ArrayList<String> teacherNames = new ArrayList<String>();
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select " + shortNameColumn
				+ " from " + tableName);
		while (results.next()) {
			teacherNames.add(results.getString(1));
		}
		results.close();
		stmt.close();
		return teacherNames;
	}

	private ArrayList<Integer> selectID() throws SQLException {
		ArrayList<Integer> teacherID = new ArrayList<Integer>();
		ResultSet results = stmt.executeQuery("select " + IDcolumn + " from "
				+ tableName);
		while (results.next()) {
			teacherID.add(results.getInt(1));
		}
		results.close();
		return teacherID;
	}

}
