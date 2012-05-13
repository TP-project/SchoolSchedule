package org.elsys.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.elsys.DatabaseConnection.Database;

public class ClassTable extends Database{
	public static final String tableName = "Class";
	public static final String IDcolumn = "id";
	public static final String classNameColumn = "className";
	public static final String specificationColumn = "specificationID";

	public void create() throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("create table " + tableName + "(" + IDcolumn
				+ " int," + classNameColumn + " varchar(12) primary key,"
				+ specificationColumn + " int)");
		stmt.close();
	}

	public void insert(String schoolClass, int specID) throws SQLException {
		stmt = conn.createStatement();
		int id = (selectID().size() == 0) ? 1 : selectID().get(
				selectID().size() - 1) + 1;
		stmt.execute("insert into " + tableName + " values (" + id + ",'"
				+ schoolClass + "'," + specID + ")");
		stmt.close();
	}

	public void delete(String schoolClass) throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("delete from " + tableName + " where " + classNameColumn
				+ " = '" + schoolClass + "'");
		stmt.close();
	}

	public ArrayList<String> selectClass() throws SQLException {
		ArrayList<String> classes = new ArrayList<String>();
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select " + classNameColumn
				+ " from " + tableName);
		while (results.next()) {
			classes.add(results.getString(1));
		}
		results.close();
		stmt.close();
		return classes;
	}

	public ArrayList<String> selectSpecification() throws SQLException {
		ArrayList<String> specifications = new ArrayList<String>();
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select "
				+ SpecificationTable.nameColumn + " from " + tableName + ", "
				+ SpecificationTable.tableName + " where " + tableName + "."
				+ specificationColumn + "=" + SpecificationTable.tableName
				+ "." + SpecificationTable.IDcolumn);
		while (results.next()) {
			specifications.add(results.getString(1));
		}
		results.close();
		stmt.close();
		return specifications;
	}

	private ArrayList<Integer> selectID() throws SQLException {
		ArrayList<Integer> classID = new ArrayList<Integer>();
		ResultSet results = stmt.executeQuery("select id from " + tableName);
		while (results.next()) {
			classID.add(results.getInt(1));
		}
		results.close();
		return classID;
	}

	public int getID(String className) throws SQLException {
		int res;
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select id from " + tableName + " where " + classNameColumn + "='" + className + "'");
		if (results.next()) {
			res = results.getInt(1);
		} else return -1;
		results.close();
		stmt.close();
		return res;
	}
	
	public boolean haveString(String str) throws SQLException {
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select " + classNameColumn  + " from " + tableName);
		while (results.next()) {
			if(results.getString(1).contentEquals(str)) {
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
