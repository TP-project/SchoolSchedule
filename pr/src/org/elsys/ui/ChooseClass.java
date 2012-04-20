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
	private Display display = new Display();
    Shell shell = new Shell(display);
	
    public ChooseClass() {
    	shell.setText("Classes");
    	shell.setLayout(new GridLayout(1, true));
 		Label label = new Label(shell, SWT.CENTER);
 		label.setText("Choose class.");
    	
	}
    
    public void makeButton(String text){
		Button b = new Button(shell, SWT.PUSH);
		b.setText(text);
		pushButton(b);
	}
    
    public void pushButton(Button b){		
		b.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {
				shell.close();
				display.dispose();
			    IsSpecialized spec = new IsSpecialized();
			    spec.open();
			}    
		});
	}
	 
	 	public void open(){
	 		makeButton("a");
	 		makeButton("b");
	 		makeButton("v");
	 		makeButton("g");
			
			shell.open();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
			    }
			}
			display.dispose();
	 	}
	 
}
