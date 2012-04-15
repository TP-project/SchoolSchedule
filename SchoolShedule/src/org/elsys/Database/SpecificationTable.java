package org.elsys.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SpecificationTable extends Database{
	public static final String tableName="Specification";
	public static final String IDcolumn="id";
	public static final String nameColumn="name";
	public SpecificationTable() {
		createDatabaseConnection();
	}
	
	public void create() {
		try {
			stmt = conn.createStatement();
			stmt.execute("create table " + tableName
					+ "(" + IDcolumn + " int primary key," + nameColumn + " varchar(15))");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void insert(String specification) {
		try {
			stmt = conn.createStatement();
			int id=(selectID().size()==0) ? 1:selectID().get(selectID().size()-1)+1;
			stmt.execute("insert into " + tableName + " values (" + id + ",'" + specification+"')");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}

	public ArrayList<String> selectSpecification() {
			ArrayList<String> subjects = new ArrayList<String>();
			try {
				stmt = conn.createStatement();
				ResultSet results = stmt.executeQuery("select " + nameColumn + " from " + tableName);
				while (results.next()) {
					subjects.add(results.getString(1));
				}
				results.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return subjects;
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