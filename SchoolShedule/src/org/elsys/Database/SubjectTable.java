package org.elsys.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectTable extends Database {
	public static final String tableName = "Subject";
	public static final String IDcolumn = "id";
	public static final String nameColumn = "name";

	public SubjectTable() {
		createDatabaseConnection();
	}

	public void create() throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("create table " + tableName + "(" + IDcolumn
				+ " int primary key," + nameColumn + " varchar(30))");
		stmt.close();
	}

	public void insert(String subject) throws SQLException {
		stmt = conn.createStatement();
		int id = (selectID().size() == 0) ? 1 : selectID().get(
				selectID().size() - 1) + 1;
		stmt.execute("insert into " + tableName + " values (" + id + ",'"
				+ subject + "')");
		stmt.close();
	}

	public void delete(String subject) throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("delete from " + tableName + " where " + nameColumn
				+ " = '" + subject + "'");
		stmt.close();
	}

	public ArrayList<String> selectSubject() throws SQLException {
		ArrayList<String> subjects = new ArrayList<String>();
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select " + nameColumn + " from "
				+ tableName);
		while (results.next()) {
			subjects.add(results.getString(1));
		}
		results.close();
		stmt.close();
		return subjects;
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

}
