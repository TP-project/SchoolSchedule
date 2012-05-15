package org.elsys.ui;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.elsys.Database.ContactTable;
import org.elsys.Database.GeneralTable;
import org.elsys.Database.SubjectTable;
import org.elsys.Database.TeacherTable;

public class Year {
	
	private Display display = new GetData().getDisplay();
	private Shell shell = new Shell(display);
	
	public Year() {
		open();

	}

	public void DropDown(GeneralTable table, Combo yearDD) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			list = table.selectYear();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String s : list) {
			yearDD.add(s);
		}

	}

	private void createContents() {
		final Text yearDD = new Text(shell, SWT.BORDER | SWT.DROP_DOWN);
		final Combo term = new Combo(shell, SWT.BORDER | SWT.Deactivate);
		term.add("1");
		term.add("2");
		GeneralTable gT = new GeneralTable();
		// DropDown(gT, yearDD);
		Button ok = new Button(shell, SWT.PUSH);
		ok.setText("Добре");
		ok.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (yearDD.getText().length() > 10) {
					
					new Message("Годината трябва да е не по-дълга от 10 символа.");
				} else if (!(yearDD.getText().equals("") || term.getText()
						.equals(""))) {
					new SelectGrade(yearDD.getText(), term.getText());
					shell.close();
				} else {
					new Message(
							"Има празни полета. Моля попълнете нужната информация");
				}
			}
		});

	}

	public void open() {
		shell.setText("Година");
		shell.setLayout(new GridLayout(2, false));
		shell.setLocation(270, 215);

		new Label(shell, SWT.LEFT).setText("Година:");
		new Label(shell, SWT.LEFT).setText("Срок:");

		createContents();
		shell.pack();
		shell.open();
	}

}
