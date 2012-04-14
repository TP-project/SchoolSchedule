package org.elsys.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherTable extends Database{
	private String tableName="Teacher11";
	public TeacherTable() {
		createDatabaseConnection();
	}
	
	public TeacherTable(String tableName) {
		this.tableName=tableName;
		createDatabaseConnection();
	}
	public void create() {
		try {
			stmt = conn.createStatement();
			stmt.execute("create table " + tableName
					+ "(id int primary key,name varchar(12),short_name varchar(5))");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void insert(String name, String shortName) {
		try {
			stmt = conn.createStatement();
			int id=(selectID().size()==0) ? 1:selectID().get(selectID().size()-1)+2;
			stmt.execute("insert into " + tableName + " values (" + id + ",'" + name+"','" + shortName + "')");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}

	public ArrayList<String> selectName() {
			ArrayList<String> teacherNames = new ArrayList<String>();
			try {
				stmt = conn.createStatement();
				ResultSet results = stmt.executeQuery("select * from " + tableName);
				while (results.next()) {
					teacherNames.add(results.getString(2));
				}
				results.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return teacherNames;
	}
	
	public ArrayList<String> selectShortName() {
		ArrayList<String> teacherNames = new ArrayList<String>();
		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + tableName);
			while (results.next()) {
				teacherNames.add(results.getString(3));
			}
			results.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacherNames;
}


	public ArrayList<Integer> selectID() {
		ArrayList<Integer> teacherID = new ArrayList<Integer>();
		try {
			ResultSet results = stmt.executeQuery("select * from " + tableName);
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
