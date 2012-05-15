package org.elsys.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class ChooseClass {

	private Display display = new GetData().getDisplay();
	private Shell shell = new Shell(display);

	public ChooseClass(String grade, String year, String term) {
		shell.setText("Изберете паралелка");
		shell.setLocation(270, 215);
		shell.setLayout(new GridLayout(1, false));
		Label label = new Label(shell, SWT.CENTER);
		label.setText("Кой " + grade + "?");
		open(grade, year, term);
	}

	public void makeButton(String text, String year, String term) {
		Button b = new Button(shell, SWT.PUSH);
		b.setText(text);
		pushButton(b, text, year, term);
	}

	public void pushButton(Button b, final String text, final String year,
			final String term) {
		b.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				shell.close();
				 new ChooseSpecification(text, year, term);
				// spec.open();
			}
		});
	}

	public void open(String grade, String year, String term) {
		makeButton(grade + "а", year, term);
		makeButton(grade + "б", year, term);
		makeButton(grade + "в", year, term);
		makeButton(grade + "г", year, term);

		shell.open();
	}

}