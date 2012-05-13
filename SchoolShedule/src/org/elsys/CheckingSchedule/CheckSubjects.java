package org.elsys.CheckingSchedule;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.elsys.Database.GeneralTable;
import org.elsys.DatabaseConnection.Database;
import org.elsys.Spreadsheet.Cell;
import org.elsys.Spreadsheet.Spreadsheet;

import com.google.gdata.util.ServiceException;

public class CheckSubjects {
	private static final int rowForYear = 1;
	private static final int colForYear = 14;
	private static final int rowForTerm = 1;
	private static final int colForTerm = 13;
	private static final int classRow = 2;
	private static final int firstClassCol = 4;
	private static final int secondClassCol = 13;
	private static final int thirdClassCol = 22;
	private static final int fourthClassCol = 31;
	private static final int startClassRow = 2;
	private static final int endClassRow = 50;
	private static final int firstClassSubjectsCol = 4;
	private static final int secondClassSubjectsCol = 13;
	private static final int thirdClassSubjectsCol = 22;
	private static final int fourthClassSubjectsCol = 31;

	private Spreadsheet sp;
	private GeneralTable table = new GeneralTable();

	public CheckSubjects(Spreadsheet sp) {
		this.sp = sp;
	}

	private ArrayList<String> getExtraSubjects(Cell schoolClass, int col) {
		Cell year = new Cell(rowForYear, colForYear);
		Cell termCell = new Cell(rowForTerm, colForTerm);
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		ArrayList<String> extraSubjects = new ArrayList<String>();

		try {
			extraSubjects.add(sp.getCellValue(schoolClass).substring(0, 3));
			res = table.selectEntries(sp.getCellValue(year),
					sp.getCellValue(termCell).charAt(0) - 48,
					sp.getCellValue(schoolClass).substring(0, 3));
			for (int i = 0; i < res.size(); i++) {
				if (sp.search(startClassRow, endClassRow, col, col,
						res.get(i).get(0)).size() > Integer.parseInt(res.get(i)
						.get(1))) {
					extraSubjects.add(res.get(i).get(0));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return extraSubjects;

	}

	public ArrayList<ArrayList<String>> checkExtraSubjects(int sheet) {
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		try {
			Database db = new Database();
			db.createDatabaseConnection();
			sp.loadSheet(sheet);

			Cell firstClass = new Cell(classRow, firstClassCol);
			Cell secondClass = new Cell(classRow, secondClassCol);
			Cell thirdClass = new Cell(classRow, thirdClassCol);
			Cell fourthClass = new Cell(classRow, fourthClassCol);

			res.add(getExtraSubjects(firstClass, firstClassSubjectsCol));
			res.add(getExtraSubjects(secondClass, secondClassSubjectsCol));
			res.add(getExtraSubjects(thirdClass, thirdClassSubjectsCol));
			res.add(getExtraSubjects(fourthClass, fourthClassSubjectsCol));

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return res;
	}

}
