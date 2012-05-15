package org.elsys.ui;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
import org.elsys.Database.ClassTable;
import org.elsys.Database.SpecificationTable;

public class ChooseSpecification {

	private Display display = new GetData().getDisplay();
	private Shell shell = new Shell(display);
	
	public ChooseSpecification(final String text,  String year,
			String term) {
		new Label(shell, SWT.CENTER).setText("Профил");
		shell.setLayout(new GridLayout(1, false));
		shell.setLocation(270, 215);
		createContent(text, year, term);

		open();

	}

	public void DropDownSetInfo(Combo specDD, SpecificationTable spT) {
		ArrayList<String> arr = new ArrayList<String>();
		try {
			arr = spT.selectSpecification();
		} catch (SQLException e) {
			new Message("Възникна неочаквана грешка!");
			e.printStackTrace();
		}
		for (String s : arr) {
			specDD.add(s);
		}
	}

	public void createContent(final String text, final String year, final String term) {
		final Combo specDD = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER);
		final SpecificationTable sp = new SpecificationTable();
		DropDownSetInfo(specDD, sp);
		Button ok = new Button(shell, SWT.PUSH);
		ok.setText("Добре");

		ok.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {

				//ClassTable cl = new ClassTable();
				try {
					if (!sp.haveString(specDD.getText())) {
						sp.insert(specDD.getText());
					}
				
				} catch (SQLIntegrityConstraintViolationException ex) {
					new Message(
							"Грешка! Този запис не може да бъде записан!");
				} catch (SQLException e) {
					new Message("Възникна неочаквана грешка!");
				e.printStackTrace();
				}
				shell.close();
				new EnterScheduleField(text, year, term);

			}
		});
	}

	public void open() {
		shell.pack();
		shell.open();
	}

}