package org.elsys.ui;

import java.io.IOException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.elsys.CheckingSchedule.SchoolSchedule;
import org.elsys.Database.ContactTable;
import org.elsys.Database.GeneralTable;
import org.elsys.Database.ScheduleTable;
import org.elsys.Database.TeacherTable;
import org.elsys.Spreadsheet.Spreadsheet;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

public class Checking {

	private final Spreadsheet sheet = new GetData().getSheet();
	private final SchoolSchedule ss = new SchoolSchedule(sheet);
	private Display display = new GetData().getDisplay();
	private Shell shell = new Shell(display);

	public Checking() {
		shell.setLayout(new GridLayout(3, false));
		shell.setLocation(120, 300);

		open();

	}

	private void createContents() {

		new Label(shell, SWT.LEFT).setText("Година:");
		new Label(shell, SWT.LEFT).setText("Срок:");
		new Label(shell, SWT.LEFT).setText("Клас:");

	}

	public void createLine() {
		Text year = new Text(shell, SWT.BORDER);

		Combo term = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER
				| SWT.Deactivate);
		term.add("1");
		term.add("2");

		Combo grade = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER
				| SWT.Deactivate);
		grade.add("8");
		grade.add("9");
		grade.add("10");
		grade.add("11");
		grade.add("12");
		makeCheckButton(year, term, grade);
		buttonFillTeaches(year, term, grade);
		buttonFillRooms(year, term, grade);

	}

	private void showExtraSubjects(int sheetIndex) {
		ArrayList<ArrayList<String>> list;
		try {
			list = ss.checkForExtraSubjects(sheetIndex);
			for (int i = 0; i < list.size(); i++) {
				String s1;
				String s2 = "";
				s1 = list.get(i).get(0) + " ";
				for (int l = 1; l < list.get(i).size(); l++) {
					s2 += list.get(i).get(l);
				}
				if (!s2.equals(""))
					new Message(s1+" има въведени повечв часове по " + s2);
			}
		} catch (Exception e) {
			new Message("Възникна неочаквана грешка!");
		}

	}

	public void makeCheckButton(final Text year, final Combo term,
			final Combo grade) {
		final Button newB = new Button(shell, SWT.PUSH);
		newB.setText("Провери");

		newB.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {

				if (year.getText().equals("") || term.getText().equals("")
						|| grade.getText().equals("")) {
					new Message("Има празни полета. Моля въведете необходимите данни.");
				} else {
					try {
						int sheetIndex = sheet.getSheetNumber(grade.getText(),
								year.getText(), term.getText());
						if (sheetIndex == -1) {
							new Message("Този запис не съществува. Моля въведете друг.");
						} else {
							showExtraSubjects(sheetIndex);
						}
					} catch (IOException e) {
						new Message("Възникна неочаквана грешка!");
					} catch (ServiceException e) {
						new Message("Първо влезте в акаунта си.");
					}
					shell.pack();
				}
			}

		});
	}

	public void buttonFillTeaches(final Text year, final Combo term,
			final Combo grade) {
		Button fillTeachers = new Button(shell, SWT.PUSH);
		fillTeachers.setText("Попълни учителите");

		fillTeachers.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (year.getText().equals("") || term.getText().equals("")
						|| grade.getText().equals("")) {
					new Message("Има празни полета. Моля въведете необходимите данни.");
				} else {
					try {
						int sheetIndex = sheet.getSheetNumber(grade.getText(),
								year.getText(), term.getText());
						if (sheetIndex == -1) {
							new Message("Този запис не съществува. Моля въведете друг.");
						} else {
							ss.fillTeachers(sheetIndex);
						}
					
					} catch (ServiceException e) {
						new Message("Първо влезте в акаунта си.");
					} catch (Exception e) {
						new Message("Възникна неочаквана грешка!");
					}
				}
			}
		});
	}

	public void buttonFillRooms(final Text year, final Combo term,
			final Combo grade) {
		Button fillRooms = new Button(shell, SWT.PUSH);
		fillRooms.setText("Попълни стаите");

		fillRooms.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (year.getText().equals("") || term.getText().equals("")
						|| grade.getText().equals("")) {
					new Message("Има празни полета. Моля въведете необходимите данни.");
				} else {
					try {
						int sheetIndex = sheet.getSheetNumber(grade.getText(),
								year.getText(), term.getText());
						if (sheetIndex == -1) {
							new Message("Този запис не съществува. Моля въведете друг.");
						} else {
							ss.fillRoomNumbers(sheetIndex);
						}
					} catch (ServiceException e) {
						new Message("Първо влезте в акаунта си.");
					}catch (Exception e) {
						new Message("Възникна неочаквана грешка!");
					} 
				}
			}
		});

	}

	public void open() {
		createContents();
		createLine();
		shell.pack();
		shell.open();
	}

}
