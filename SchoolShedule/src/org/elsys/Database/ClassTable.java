package org.elsys.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassTable extends Database {
	public static final String tableName = "Class";
	public static final String IDcolumn = "id";
	public static final String classNameColumn = "className";
	public static final String specificationColumn = "specificationID";

	public ClassTable() {
		createDatabaseConnection();
	}
	
	protected void finalize() throws Throwable {
		shutdownDatabase();
	}

	public void create() throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("create table " + tableName + "(" + IDcolumn
				+ " int primary key," + classNameColumn + " varchar(12),"
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
		ArrayList<String> Classes = new ArrayList<String>();
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select " + classNameColumn
				+ " from " + tableName);
		while (results.next()) {
			Classes.add(results.getString(1));
		}
		results.close();
		stmt.close();
		return Classes;
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

	public ArrayList<Integer> selectID() throws SQLException {
		ArrayList<Integer> teacherID = new ArrayList<Integer>();
		ResultSet results = stmt.executeQuery("select id from " + tableName);
		while (results.next()) {
			teacherID.add(results.getInt(1));
		}
		results.close();
		return teacherID;
	}

}
