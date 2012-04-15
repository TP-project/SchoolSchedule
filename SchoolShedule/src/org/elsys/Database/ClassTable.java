package org.elsys.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassTable extends Database{
	private String tableName="Class2";
	public ClassTable() {
		createDatabaseConnection();
	}
	
	public ClassTable(String tableName) {
		this.tableName=tableName;
		createDatabaseConnection();
	}
	public void create() {
		try {
			stmt = conn.createStatement();
			stmt.execute("create table " + tableName
					+ "(id int primary key,className varchar(12),specificationID int)");
//TODO names of columns and table name must be constants 			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void insert(String schoolClass, int specID) {
		try {
			stmt = conn.createStatement();
			int id=(selectID().size()==0) ? 2:selectID().get(selectID().size()-1)+2;
			stmt.execute("insert into " + tableName + " values (" + id + ",'" + schoolClass+"'," + specID + ")");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}

	public ArrayList<String> selectClass() {
			ArrayList<String>Classes = new ArrayList<String>();
			try {
				stmt = conn.createStatement();
				ResultSet results = stmt.executeQuery("select className from " + tableName);
				while (results.next()) {
					Classes.add(results.getString(1));
				}
				results.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Classes;
	}
	
	public ArrayList<String> selectSpecification() {
		ArrayList<String> specifications = new ArrayList<String>();
		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from Class2, Specification");
			while (results.next()) {
				specifications.add(results.getString(5));
			}
			results.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return specifications;
}

	public ArrayList<Integer> selectID() {
		ArrayList<Integer> teacherID = new ArrayList<Integer>();
		try {
			ResultSet results = stmt.executeQuery("select id from " + tableName);
			while (results.next()) {
			while(results.next()) {
				teacherID.add(results.getInt(1));
			}
			}
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacherID;
}


}
