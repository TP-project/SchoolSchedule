package org.elsys.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.elsys.Database.SubjectTable;
import org.elsys.DatabaseConnection.Database;
import org.junit.Test;

public class TestSubjectTable {

		@Test
		public void testCreateTable() throws InstantiationException,
				IllegalAccessException, ClassNotFoundException, SQLException {
			boolean exc = false;
			Database d = new Database();
			SubjectTable s = new SubjectTable();
			d.createDatabaseConnection("test");
			try {
				s.create();
				s.create();
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
			SubjectTable s = new SubjectTable();
			s.insert("test");
			assertTrue(s.selectSubject().size() == 1);
			s.delete("test");
		}

		@Test
		public void testDelete() throws SQLException {
			SubjectTable s = new SubjectTable();
			s.insert("test");
			s.delete("test");
			assertTrue(s.selectSubject().size() == 0);
		}
		
		@Test
		public void testSelectSubject() throws SQLException{
			SubjectTable s = new SubjectTable();
			s.insert("test");
			assertTrue(s.selectSubject().get(0).compareTo("test")==0);
			s.delete("test");
		}
		
		@Test
		public void testGetID() throws SQLException{
			SubjectTable s = new SubjectTable();
			s.insert("test");
			s.insert("test1");
			s.delete("test");
			assertTrue(s.getID("test1")==2);
			s.delete("test1");
		}
		
		@Test
		public void testGetIDWrongInfo() throws SQLException{
			SubjectTable s = new SubjectTable();
			s.insert("test");
			s.insert("test1");
			s.delete("test");
			assertTrue(s.getID("tdsfest1")==-1);
			s.delete("test1");
		}
		
		@Test
		public void testHaveString() throws SQLException{
			SubjectTable s = new SubjectTable();
			s.insert("test");
			assertTrue(s.haveString("test"));
			assertFalse(s.haveString("1335"));
			s.delete("test");
		}
		
		@Test
		public void testDrop(){
			boolean exc=false;
			SubjectTable s = new SubjectTable();
			try {
				s.drop();
				s.drop();
			} catch (SQLException e) {
				assertTrue(true);
				exc=true;
			}
			try {
				s.create();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(!exc) {
				fail();
			}
		}
}
