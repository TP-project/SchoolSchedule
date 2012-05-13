package org.elsys.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.elsys.DatabaseConnection.Database;

public class NoteTable extends Database {
	public static final String tableName = "Notes";
	public static final String IDcolumn = "id";
	public static final String noteColumn = "note";
	public static final String teacherColumn = "teacherID";
	public static final String otherNoteColumn = "other_note";
	public static final String isItRequiredColumn = "is_it_required";

	public void create() throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("create table " + tableName + "(" + IDcolumn
				+ " int primary key," + noteColumn + " varchar(100),"
				+ otherNoteColumn + " varchar(100)," + isItRequiredColumn
				+ " boolean," + teacherColumn + " int)");
		stmt.close();
	}

	public void insert(String note, String otherNote, boolean isItRequired, int teacherID ) throws SQLException {
		stmt = conn.createStatement();
		int id = (selectID().size() == 0) ? 1 : selectID().get(
				selectID().size() - 1) + 1;
		stmt.execute("insert into " + tableName + " values (" + id + ",'"
				+ note + "','" + otherNote + "'," + isItRequired + "," + teacherID + ")");
		stmt.close();
	}

	public void delete(String note) throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("delete from " + tableName + " where " + noteColumn
				+ " = '" + note + "'");
		stmt.close();
	}

	public ArrayList<String> selectNote() throws SQLException {
		ArrayList<String> notes = new ArrayList<String>();
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select " + noteColumn + " from "
				+ tableName);
		while (results.next()) {
			notes.add(results.getString(1));
		}
		results.close();
		stmt.close();
		return notes;
	}

	public ArrayList<String> selectOtherNote() throws SQLException {
		ArrayList<String> otherNotes = new ArrayList<String>();
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select " + otherNoteColumn + " from "
				+ tableName);
		while (results.next()) {
			otherNotes.add(results.getString(1));
		}
		results.close();
		stmt.close();
		return otherNotes;
	}
	
	public ArrayList<String> selectIsItRequired() throws SQLException {
		ArrayList<String> requireds = new ArrayList<String>();
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select " + isItRequiredColumn + " from "
				+ tableName);
		while (results.next()) {
			requireds.add(results.getString(1));
		}
		results.close();
		stmt.close();
		return requireds;
	}
	
	public ArrayList<String> selectTeacher() throws SQLException {
		ArrayList<String> teachers = new ArrayList<String>();
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select "
				+ TeacherTable.nameColumn + " from " + tableName + ", "
				+ TeacherTable.tableName + " where " + tableName + "."
				+ teacherColumn + "=" + TeacherTable.tableName + "."
				+ TeacherTable.IDcolumn);
		while (results.next()) {
			teachers.add(results.getString(1));
		}
		results.close();
		stmt.close();
		return teachers;
	}

	public ArrayList<Integer> selectID() throws SQLException {
		ArrayList<Integer> ID= new ArrayList<Integer>();
		ResultSet results = stmt.executeQuery("select " + IDcolumn + " from " + tableName);
		while (results.next()) {
		ID.add(results.getInt(1));
		}
		results.close();
		return ID;
	}
	
	public void drop() throws SQLException {
		stmt = conn.createStatement();
		stmt.execute("drop table " + tableName);
		stmt.close();
	}
}
