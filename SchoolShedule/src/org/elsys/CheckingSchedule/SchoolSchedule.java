package org.elsys.CheckingSchedule;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.elsys.Database.GeneralTable;
import org.elsys.Database.TeacherTable;
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

	private ArrayList<String> getExtraSubjects(Cell schoolClass, int col)
			throws IOException, ServiceException, SQLException {
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		ArrayList<String> extraSubjects = new ArrayList<String>();

		extraSubjects.add(sp.getCellValue(schoolClass).substring(0, 3));
		res = table.selectEntries(sp.getCellValue(SpInfo.year), sp
				.getCellValue(SpInfo.termCell).substring(0, 1), sp
				.getCellValue(schoolClass).substring(0, 3));
		for (int i = 0; i < res.size(); i++) {
			if (sp.search(SpInfo.startClassRow, SpInfo.endClassRow, col, col,
					res.get(i).get(0)).size() > Integer.parseInt(res.get(i)
					.get(1))) {
				extraSubjects.add(res.get(i).get(0));
			}
		}

		return extraSubjects;

	}

	public ArrayList<ArrayList<String>> checkForExtraSubjects(int sheet)
			throws IOException, ServiceException, SQLException {
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		sp.loadSheet(sheet);

		res.add(getExtraSubjects(SpInfo.firstClass,
				SpInfo.firstClassSubjectsCol));
		res.add(getExtraSubjects(SpInfo.secondClass,
				SpInfo.secondClassSubjectsCol));
		res.add(getExtraSubjects(SpInfo.thirdClass,
				SpInfo.thirdClassSubjectsCol));
		res.add(getExtraSubjects(SpInfo.fourthClass,
				SpInfo.fourthClassSubjectsCol));

		return res;
	}

	public void fillRoomNumbers(int sheet, Cell schoolClass, int col)
			throws SQLException, IOException, ServiceException {

		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();

		sp.loadSheet(sheet);
		res = table.selectEntries(sp.getCellValue(SpInfo.year), sp
				.getCellValue(SpInfo.termCell).substring(0, 1), sp
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
	}

	public void fillRoomNumbers(int sheet) throws SQLException, IOException,
			ServiceException {
		fillRoomNumbers(sheet, SpInfo.firstClass, SpInfo.firstClassSubjectsCol);
		fillRoomNumbers(sheet, SpInfo.secondClass,
				SpInfo.secondClassSubjectsCol);
		fillRoomNumbers(sheet, SpInfo.thirdClass, SpInfo.thirdClassSubjectsCol);
		fillRoomNumbers(sheet, SpInfo.fourthClass,
				SpInfo.fourthClassSubjectsCol);
	}

	public void fillTeachers(int sheet, Cell schoolClass, int col)
			throws SQLException, IOException, ServiceException {

		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		sp.loadSheet(sheet);
		res = table.selectEntries(sp.getCellValue(SpInfo.year), sp
				.getCellValue(SpInfo.termCell).substring(0, 1), sp
				.getCellValue(schoolClass).substring(0, 3));
		for (int j = 0; j < res.size(); j++) {
			for (int i = SpInfo.startClassRow; i < SpInfo.endClassRow; i++) {
				if (sp.getCellValue(new Cell(i, col)) != null) {
					if (sp.getCellValue(new Cell(i, col)).compareTo(
							res.get(j).get(0)) == 0) {
						Cell teacher = new Cell(i, col + 3);
						sp.setCellValue(teacher, res.get(j).get(2));
					}
				}
			}
		}
	}

	public void fillTeachers(int sheet) throws SQLException, IOException,
			ServiceException {
		fillTeachers(sheet, SpInfo.firstClass, SpInfo.firstClassSubjectsCol);
		fillTeachers(sheet, SpInfo.secondClass,
				SpInfo.secondClassSubjectsCol);
		fillTeachers(sheet, SpInfo.thirdClass, SpInfo.thirdClassSubjectsCol);
		fillTeachers(sheet, SpInfo.fourthClass,
				SpInfo.fourthClassSubjectsCol);
	}
}
