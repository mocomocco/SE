import javax.swing.*;
import java.io.*;
import java.io.File;
import javax.swing.filechooser.*;

class OpFileDialogs extends JFrame
{
	JFileChooser fc;
	String targetjson;

	public OpFileDialogs()
	{
		try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {} // setting Look And Feel
		
		fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files", "json");
		fc.setFileFilter(filter);
		fc.setMultiSelectionEnabled(false);

		int returnVal = fc.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File selectFile = fc.getSelectedFile();
			String filename = fc.getSelectedFile().getName();
			String targetjson = selectFile.getAbsolutePath();
			try{
				new Display(filename, targetjson);
			} catch (Exception e){
				e.printStackTrace();
			}
		} else if(returnVal == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "File open has been canceled");
		}
	}
}