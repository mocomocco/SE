import javax.swing.*;
import java.io.*;
import java.io.File;

class OpFileDialogs extends JFrame
{
	JFileChooser fc;

	public OpFileDialogs()
	{
		try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {} // setting Look And Feel
		
		fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File selectFile = fc.getSelectedFile(); //fix to get file
		}
	}
}
