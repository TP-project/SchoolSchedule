package org.elsys.ui;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.elsys.Database.ClassTable;
import org.elsys.Database.GeneralTable;
import org.elsys.Database.RoomTable;
import org.elsys.Database.ScheduleTable;
import org.elsys.Database.SpecificationTable;
import org.elsys.Database.SubjectTable;
import org.elsys.Database.TeacherTable;

public class EnterScheduleField {

	private Display display = new GetData().getDisplay();
	private Shell shell = new Shell(display);

	EnterScheduleField(String text, String year, String term) {
		shell.setText("Предмети");
		shell.setLayout(new GridLayout(7, false));
		shell.setLocation(270, 215);
		open(text, year, term);

	}

	private void createContents() {
		new Label(shell, SWT.LEFT).setText("Предмет:");
		new Label(shell, SWT.LEFT).setText("Часове седмично:");
		new Label(shell, SWT.LEFT).setText("Стая:");
		new Label(shell, SWT.LEFT).setText("Учител:");
		new Label(shell, SWT.LEFT).setText(" ");
		new Label(shell, SWT.LEFT).setText(" ");
		new Label(shell, SWT.LEFT).setText(" ");
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;

	}

	public void DropDownTch(TeacherTable table, Combo teacherDD) {
		ArrayList<String> teacherList = new ArrayList<String>();
		try {
			teacherList = table.selectName();
		} catch (SQLException e) {
			new Message("Възникна неочаквана грешка!");
		}
		for (String s : teacherList) {
			teacherDD.add(s);
		}

	}

	public void DropDownSub(SubjectTable table, Combo subDD) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			list = table.selectSubject();
		} catch (SQLException e) {
			new Message("Възникна неочаквана грешка!");
		}
		for (String s : list) {
			subDD.add(s);
		}

	}

	public void createLine(final String text, final String year,
			final String term) {
		final Combo subDD = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER);
		final Text hours = new Text(shell, SWT.BORDER);
		final Text cab = new Text(shell, SWT.BORDER);
		final Combo teacherDD = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER);

		final TeacherTable tchT = new TeacherTable();
		final SubjectTable subT = new SubjectTable();
		final RoomTable roomT = new RoomTable();
		final ScheduleTable schT = new ScheduleTable();
		final ClassTable clT = new ClassTable();
		final GeneralTable gT = new GeneralTable();
		DropDownTch(tchT, teacherDD);
		DropDownSub(subT, subDD);

		Button save = new Button(shell, SWT.PUSH);
		save.setText("Запиши");

		save.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (hours.getText().equals("")
						|| teacherDD.getText().equals("")
						|| subDD.getText().equals("")) {
					new Message(
							"Само полето стая може да бъде празно. Моля попълнете нужната информация.");
				} else {
					try {

						if (!tchT.haveString(teacherDD.getText())) {
							tchT.insert(teacherDD.getText(), "");
						}
						if (!subT.haveString(subDD.getText())) {
							subT.insert(subDD.getText());
						}
						if (!roomT.haveString(cab.getText())) {
							roomT.insert(cab.getText());
						}

						schT.insert(schT.lastId(), subT.getID(subDD.getText()),
								Integer.parseInt(hours.getText()),
								tchT.getID(teacherDD.getText()),
								roomT.getID(cab.getText()), clT.getID(text));
						gT.insert(year, term, schT.lastId() - 1);
					} catch (NumberFormatException ex) {
						new Message("Часове седмично трябва да бъде число.");
					} catch (SQLDataException e) {
						new Message(
								"Предметът и учителят трябва да бъде най-много 30 символа, а кабинетът - 5.");
					} catch (SQLException e) {
						new Message("Възникна неочаквана грешка.");
					}
					shell.pack();
				}
			}
		});

		DeleteButton(subDD, hours, cab, teacherDD);
		NewButton(text, year, term);

	}

	public void DeleteButton(final Combo subDD, final Text hours,
			final Text cab, final Combo teacherDD) {
		Button del = new Button(shell, SWT.PUSH);
		del.setText("Изтрий");
		final ScheduleTable schT = new ScheduleTable();

		del.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				try {
					schT.delete(schT.lastId());
				} catch (SQLException e) {
					e.printStackTrace();
				}

				subDD.setText("");
				hours.setText("");
				cab.setText("");
				teacherDD.setText("");
				shell.pack();
			}
		});
	}

	public void NewButton(final String text, final String year,
			final String term) {
		final Button newB = new Button(shell, SWT.PUSH);
		newB.setText("Нов запис");
		newB.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {
				createLine(text, year, term);
				newB.setVisible(false);
				shell.pack();
			}
		});

	}

	public void open(String text, String year, String term) {
		createContents();
		createLine(text, year, term);

		shell.pack();
		shell.open();

	}

}
