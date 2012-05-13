package org.elsys.CheckingSchedule;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.elsys.Database.GeneralTable;
import org.elsys.DatabaseConnection.Database;
import org.elsys.Spreadsheet.Cell;
import org.elsys.Spreadsheet.Spreadsheet;

import com.google.gdata.util.ServiceException;

public class SchoolSchedule {	
	private Spreadsheet sp;
	private GeneralTable table = new GeneralTable();

	public SchoolSchedule(Spreadsheet sp) {
		this.sp = sp;
	}

	private ArrayList<String> getExtraSubjects(Cell schoolClass, int col) {
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		ArrayList<String> extraSubjects = new ArrayList<String>();

		try {
			extraSubjects.add(sp.getCellValue(schoolClass).substring(0, 3));
			res = table.selectEntries(sp.getCellValue(SpInfo.year),
					sp.getCellValue(SpInfo.termCell).charAt(0) - 48,
					sp.getCellValue(schoolClass).substring(0, 3));
			for (int i = 0; i < res.size(); i++) {
				if (sp.search(SpInfo.startClassRow, SpInfo.endClassRow, col, col,
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

	public ArrayList<ArrayList<String>> checkForExtraSubjects(int sheet) {
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		try {
			Database db = new Database();
			db.createDatabaseConnection();
			sp.loadSheet(sheet);

			res.add(getExtraSubjects(SpInfo.firstClass, SpInfo.firstClassSubjectsCol));
			res.add(getExtraSubjects(SpInfo.secondClass, SpInfo.secondClassSubjectsCol));
			res.add(getExtraSubjects(SpInfo.thirdClass, SpInfo.thirdClassSubjectsCol));
			res.add(getExtraSubjects(SpInfo.fourthClass, SpInfo.fourthClassSubjectsCol));

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public void fillRoomNumbers(Cell schoolClass, int col) {

		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();

		Database db = new Database();
		db.createDatabaseConnection();

		try {
			sp.loadSheet(0);
			res = table.selectEntries(sp.getCellValue(SpInfo.year), sp
					.getCellValue(SpInfo.termCell).charAt(0) - 48, sp
					.getCellValue(schoolClass).substring(0, 3));
			for (int j = 0; j < res.size(); j++) {
				for (int i = SpInfo.startClassRow; i < SpInfo.endClassRow; i++) {
					if (sp.getCellValue(new Cell(i, col)) != null) {
						if (sp.getCellValue(new Cell(i, col)).compareTo(
								res.get(j).get(0)) == 0) {
							Cell room = new Cell(i, col + 2);
							sp.setCellValue(room, res.get(j).get(3));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

}
