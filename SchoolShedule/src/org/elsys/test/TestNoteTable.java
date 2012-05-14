package org.elsys.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.elsys.Database.NoteTable;
import org.elsys.Database.TeacherTable;
import org.elsys.DatabaseConnection.Database;
import org.junit.Test;

public class TestNoteTable {

		@Test
		public void testCreateTable() throws InstantiationException,
				IllegalAccessException, ClassNotFoundException, SQLException {
			boolean exc = false;
			Database d = new Database();
			NoteTable r = new NoteTable();
			d.createDatabaseConnection("test");
			try {
				r.create();
				r.create();
			} catch (SQLException e) {
				exc = true;
				assertTrue(true);
			}
			if (!exc) {
				fail();
			}
		}

		@Test
		public void testInsert() throws SQLException {
			NoteTable n = new NoteTable();
			n.insert("test", "test1", false, 1);
			assertTrue(n.selectNote().size() == 1);
			n.delete("test");
		}

		@Test
		public void testDelete() throws SQLException {
			NoteTable n = new NoteTable();
			n.insert("test", "test1", false, 1);
			n.delete("test");
			assertTrue(n.selectNote().size() == 0);
		}
		
		@Test
		public void testSelectNote() throws SQLException{
			NoteTable n = new NoteTable();
			n.insert("test", "test1", false, 1);
			assertTrue(n.selectNote().get(0).compareTo("test")==0);
			n.delete("test");
		}
		
		@Test
		public void testSelectOtherNote() throws SQLException{
			NoteTable n = new NoteTable();
			n.insert("test", "test1", false, 1);
			assertTrue(n.selectOtherNote().get(0).compareTo("test1")==0);
			n.delete("test");
		}
		
		@Test
		public void testSelectIsItRequaired() throws SQLException{
			NoteTable n = new NoteTable();
			n.insert("test", "test1", false, 1);
			assertTrue(n.selectIsItRequired().get(0).compareTo("false")==0);
			n.delete("test");
		}
		
		@Test
		public void testSelectTeacher() throws SQLException {
			TeacherTable t = new TeacherTable();
			NoteTable n = new NoteTable();
			n.insert("test", "test1", false, 1);
			try {
				t.insert("test", "te");
			} catch (SQLException e) {
				t.create();
				t.insert("test", "te");
			}
			assertTrue(n.selectTeacher().get(0).compareTo(t.selectName().get(0)) == 0);
			n.delete("test");
			t.delete("test");
		}
		
		@Test
		public void testDrop(){
			boolean exc=false;
			NoteTable n = new NoteTable();
			try {
				n.drop();
				n.drop();
			} catch (SQLException e) {
				assertTrue(true);
				exc=true;
			}
			try {
				n.create();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(!exc) {
				fail();
			}
		}
}
