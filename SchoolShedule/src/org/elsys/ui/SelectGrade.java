package org.elsys.ui;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class SelectGrade {
	
	private Display display = new GetData().getDisplay();
	private Shell shell = new Shell(display);
	
	public SelectGrade(String year, String term){
		Label label=new Label(shell, SWT.NONE);
		label.setText("Изберете клас");
		shell.setText("Класове");
		shell.setLayout(new GridLayout(1, false));
		shell.setLocation(270,215);	
		open(year, term);
	}
		
	private void makeButton(String grade,String year, String term){
		Button b = new Button(shell, SWT.PUSH);
		b.setText(grade);
		pushButton(b, grade, year, term);
	}
	
	public void pushButton(Button b, final String grade, final String year, final String term){		
		b.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {
				shell.close();
			     new ChooseClass(grade, year, term);
			}    
		});
	}
	
	public void open(String year, String term){

		makeButton("8", year, term);
		makeButton("9", year, term);
		makeButton("10", year, term);
		makeButton("11",year, term);
		makeButton("12",year, term);
	
	
		shell.open();
		
	}
		
	
		
	}




