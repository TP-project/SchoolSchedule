package org.elsys.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleTable extends Database {
	public static final String tableName = "Shedule";
	public static final String IDcolumn = "id";
	public static final String subjectColumn = "subjectID";
	public static final String teacherColumn = "teacherID";
	public static final String roomNumberColumn = "room";
	public static final String classColumn = "classID";
	public static final String hoursPerWeekColumn = "hours_per_week";

	public ScheduleTable() {
		createDatabaseConnection();
	}

	protected void finalize() throws Throwable {
		shutdownDatabase();
	}

	public void create() throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("create table " + tableName + "(" + IDcolumn
				+ " int primary key," + subjectColumn + " int,"
				+ hoursPerWeekColumn + " int," + teacherColumn + " int,"
				+ roomNumberColumn + " int," + classColumn + " int)");
		stmt.close();
	}

	public void insert(int subjectId, int hoursPerWeek, int teacherId,
			int roomId, int classId) throws SQLException {
		stmt = conn.createStatement();
		int id = (selectID().size() == 0) ? 1 : selectID().get(
				selectID().size() - 1) + 1;
		stmt.execute("insert into " + tableName + " values (" + id + ","
				+ subjectId + "," + hoursPerWeek + "," + teacherId + ","
				+ roomId + "," + classId + ")");
		stmt.close();
	}

	public void delete(int id) throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("delete from " + tableName + " where " + IDcolumn + " = "
				+ id);
		stmt.close();
	}

	public ArrayList<String> selectEntry(int id) throws SQLException {
		ArrayList<String> entry = new ArrayList<String>();
		stmt = conn.createStatement();
		// TODO fix select statement
		ResultSet results = stmt.executeQuery("select "
				+ SubjectTable.nameColumn + " from " + tableName + ", "
				+ SubjectTable.tableName + " where " + tableName + "."
				+ subjectColumn + "=" + SubjectTable.tableName + "."
				+ SubjectTable.IDcolumn + " and " + tableName + "." + IDcolumn
				+ "=" + id);
		while (results.next()) {
			entry.add(results.getString(1));
		}

		results = stmt.executeQuery("select " + hoursPerWeekColumn
				+ " from " + tableName + " where " + tableName + "." + IDcolumn + "=" + id);
		while (results.next()) {
			entry.add(results.getString(1));
		}
		
		results = stmt.executeQuery("select " + TeacherTable.nameColumn
				+ " from " + tableName + ", " + TeacherTable.tableName
				+ " where " + tableName + "." + teacherColumn + "="
				+ TeacherTable.tableName + "." + TeacherTable.IDcolumn
				+ " and " + tableName + "." + IDcolumn + "=" + id);
		while (results.next()) {
			entry.add(results.getString(1));
		}

		results = stmt.executeQuery("select " + RoomTable.roomNumberColumn
				+ " from " + tableName + ", " + RoomTable.tableName + " where "
				+ tableName + "." + roomNumberColumn + "="
				+ RoomTable.tableName + "." + RoomTable.IDcolumn + " and "
				+ tableName + "." + IDcolumn + "=" + id);
		while (results.next()) {
			entry.add(results.getString(1));
		}

		results = stmt.executeQuery("select " + ClassTable.classNameColumn
				+ " from " + tableName + ", " + ClassTable.tableName
				+ " where " + tableName + "." + classColumn + "="
				+ ClassTable.tableName + "." + ClassTable.IDcolumn + " and "
				+ tableName + "." + IDcolumn + "=" + id);
		while (results.next()) {
			entry.add(results.getString(1));
		}

		results.close();
		stmt.close();
		return entry;
	}

	public ArrayList<Integer> selectID() throws SQLException {
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
