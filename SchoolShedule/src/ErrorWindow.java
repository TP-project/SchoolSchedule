
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class ErrorWindow {

	Display display = new Display();
	GridLayout gridLayout = new GridLayout();
	final Shell shell = new Shell(display);
	Label label=new Label(shell, SWT.NONE);
	final Button button= new Button(shell, SWT.PUSH);
	
	ErrorWindow(){
		
		shell.setText("Error!");
		shell.setBounds(80, 80, 300, 150);
		
		gridLayout.numColumns = 1;

		shell.setLayout(gridLayout);
		//final Canvas canvas = new Canvas(shell, SWT.NONE);
		//canvas.setBackground(Display.getDefault().getSystemColor(
		//		SWT.COLOR_WHITE));
		//canvas.setBounds(5, 5, 50, 50);
		
		
		label.setText("Error! This is not right!");
		
		
		
		button.setText("Okey");
			
		  button.addSelectionListener(new SelectionAdapter() {
		         public void widgetSelected(SelectionEvent event) {
		           shell.close();
		         }
		      });
		
	}
	void openWindow(){
	//GridData gridData = new GridData();
		//gridData.horizontalAlignment = GridData.CENTER;
		//gridData.grabExcessHorizontalSpace = true;
		//label.setLayoutData(gridData);
		//button.setLayoutData(gridData);
		
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
		
	

		
}