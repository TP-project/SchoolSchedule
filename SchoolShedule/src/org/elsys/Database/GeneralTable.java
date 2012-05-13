package org.elsys.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.elsys.DatabaseConnection.Database;

public class GeneralTable extends Database {
	public static final String tableName = "General_table";
	public static final String IDcolumn = "id";
	public static final String yearColumn = "year_column";
	public static final String termColumn = "term";
	public static final String scheduleColumn = "shedule_id";

	public void create() throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("create table " + tableName + "(" + IDcolumn
				+ " int primary key, " + yearColumn + " varchar(10), "
				+ termColumn + " varchar(10), " + scheduleColumn + " int)");
		stmt.close();
	}

	public void insert(String year, String term, int scheduleId)
			throws SQLException {
		stmt = conn.createStatement();
		int id = (selectID().size() == 0) ? 1 : selectID().get(
				selectID().size() - 1) + 1;
		stmt.execute("insert into " + tableName + " values (" + id + ",'"
				+ year + "','" + term + "'," + scheduleId + ")");
		stmt.close();
	}

	public void delete(int id) throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("delete from " + tableName + " where " + IDcolumn + " = "
				+ id);
		stmt.close();
	}

	public ArrayList<String> selectYear() throws SQLException {
		ArrayList<String> years = new ArrayList<String>();
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select " + yearColumn + " from "
				+ tableName);
		while (results.next()) {
			years.add(results.getString(1));
		}
		results.close();
		stmt.close();
		return years;
	}

	public ArrayList<String> selectTerm() throws SQLException {
		ArrayList<String> terms = new ArrayList<String>();
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select " + termColumn + " from "
				+ tableName);
		while (results.next()) {
			terms.add(results.getString(1));
		}
		results.close();
		stmt.close();
		return terms;
	}

	public ArrayList<ArrayList<String>> selectEntries() throws SQLException {
		ArrayList<ArrayList<String>> entries = new ArrayList<ArrayList<String>>();
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select " + scheduleColumn
				+ " from " + tableName);
		ScheduleTable table = new ScheduleTable();
		while (results.next()) {
			entries.add(table.selectEntry(results.getInt(1)));
		}
		results.close();
		stmt.close();
		return entries;
	}

	public ArrayList<ArrayList<String>> selectEntries(String year, String term)
			throws SQLException {
		ArrayList<ArrayList<String>> entries = new ArrayList<ArrayList<String>>();
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select " + scheduleColumn
				+ " from " + tableName + " where " + yearColumn + "='" + year
				+ "' and " + termColumn + "='" + term + "'");
		ScheduleTable table = new ScheduleTable();
		while (results.next()) {
			entries.add(table.selectEntry(results.getInt(1)));
		}
		results.close();
		stmt.close();
		return entries;
	}

	public ArrayList<ArrayList<String>> selectEntries(String year, String term,
			String className) throws SQLException {
		ArrayList<ArrayList<String>> entries = new ArrayList<ArrayList<String>>();
		stmt = conn.createStatement();
		ClassTable cl = new ClassTable();
		ResultSet results = stmt.executeQuery("select " + scheduleColumn
				+ " from " + tableName + "," + ScheduleTable.tableName
				+ " where " + yearColumn + "='" + year + "' and " + termColumn
				+ "='" + term + "'" + " and " + scheduleColumn + "="
				+ ScheduleTable.tableName + "." + ScheduleTable.IDcolumn
				+ " and " + ScheduleTable.classColumn + "="
				+ cl.getID(className));

		ScheduleTable table = new ScheduleTable();
		while (results.next()) {
			entries.add(table.selectEntry(results.getInt(1)));
		}
		results.close();
		stmt.close();
		return entries;
	}

	public ArrayList<Integer> selectID() throws SQLException {
		ArrayList<Integer> teacherID = new ArrayList<Integer>();
		ResultSet results = stmt.executeQuery("select " + IDcolumn + " from "
				+ tableName);
		while (results.next()) {
			teacherID.add(results.getInt(1));
		}
		results.close();
		return teacherID;
	}

	public void drop() throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("drop table " + tableName);
		stmt.close();
	}
	
	public boolean haveYear(String str) throws SQLException {
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select " + yearColumn  + " from " + tableName);
		while (results.next()) {
			if(results.getString(1).contentEquals(str)) {
				return true;
			}
		}
		return false;

	}

	public boolean haveTerm(String str) throws SQLException {
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select " + termColumn  + " from " + tableName);
		while (results.next()) {
			if(results.getString(1).contentEquals(str)) {
				return true;
			}
		}
		return false;

	}


}
