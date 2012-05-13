package org.elsys.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.elsys.DatabaseConnection.Database;

public class SpecificationTable extends Database {
	public static final String tableName = "Specification";
	public static final String IDcolumn = "id";
	public static final String nameColumn = "name";


	public void create() throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("create table " + tableName + "(" + IDcolumn
				+ " int primary key," + nameColumn + " varchar(15))");
		stmt.close();
	}

	public void insert(String specification) throws SQLException {
		stmt = conn.createStatement();
		int id = (selectID().size() == 0) ? 1 : selectID().get(
				selectID().size() - 1) + 1;
		stmt.execute("insert into " + tableName + " values (" + id + ",'"
				+ specification + "')");
		stmt.close();
	}

	public void delete(String specification) throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("delete from " + tableName + " where " + nameColumn
				+ " = '" + specification + "'");
		stmt.close();
	}

	public ArrayList<String> selectSpecification() throws SQLException {
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

	public int getID(String name) throws SQLException {
		int res;
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select id from " + tableName + " where " + nameColumn + "='" + name + "'");
		if (results.next()) {
			res = results.getInt(1);
		} else return -1;
		results.close();
		stmt.close();
		return res;
	}
	
	public void drop() throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("drop table " + tableName);
		stmt.close();
	}
}