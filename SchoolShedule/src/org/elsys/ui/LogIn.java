package org.elsys.ui;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.elsys.Database.SpecificationTable;
import org.elsys.Spreadsheet.Spreadsheet;

import com.google.gdata.util.AuthenticationException;

public class LogIn {
	
	private Display display = new GetData().getDisplay();
	private Shell shell = new Shell(display);
	
	public LogIn() {
		shell.setText("Вход");
		shell.setLayout(new GridLayout(2, false));
		shell.setLocation(100, 120);

		new Label(shell, SWT.LEFT).setText("Потребителско име:");
		new Label(shell, SWT.LEFT).setText("Парола:");
		open();
	}

	
	public void logIn() {
		final Text name = new Text(shell, SWT.BORDER);
		final Text pass = new Text(shell, SWT.PASSWORD | SWT.BORDER);
		Button logIn = new Button(shell, SWT.PUSH);
		logIn.setText("Вход");

		logIn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				Spreadsheet s = new GetData().getSheet();
				try {
					s.login(name.getText(), pass.getText());
					shell.close();
				} catch (AuthenticationException e) {
					
					new Message("Грешно потребителско име или парла.");
					name.setText("");
					pass.setText("");
					
				}
				
			}
		});
	}

	public void open() {
		logIn();

		shell.pack();
		shell.open();

	}

}
