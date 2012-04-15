package org.elsys.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherTable extends Database{
	public static final String tableName="Teacher";
	public static final String IDcolumn="id";
	public static final String shortNameColumn="short_name";
	public static final String nameColumn="name";
	public TeacherTable() {
		createDatabaseConnection();
	}
	
	public void create() {
		try {
			stmt = conn.createStatement();
			stmt.execute("create table " + tableName
					+ "(" + IDcolumn + " int primary key," + nameColumn + " varchar(12)," + shortNameColumn + " varchar(5))");
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
				ResultSet results = stmt.executeQuery("select " + nameColumn + " from " + tableName);
				while (results.next()) {
					teacherNames.add(results.getString(1));
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
			ResultSet results = stmt.executeQuery("select "+ shortNameColumn + " from " + tableName);
			while (results.next()) {
				teacherNames.add(results.getString(1));
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
			ResultSet results = stmt.executeQuery("select " + IDcolumn + " from " + tableName);
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
