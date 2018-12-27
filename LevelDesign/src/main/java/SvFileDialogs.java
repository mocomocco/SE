import javax.swing.*;
import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

class SvFileDialogs extends JFrame
{
	JFileChooser fc;
	String fileName;
	Level thislevel;
	public SvFileDialogs(String namet,Level level)
	{
		thislevel=level;
		try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {} // setting Look And Feel
		
		fc = new JFileChooser();
		fc.setSelectedFile(new File(namet));
		BufferedWriter writer;
		
		int retriVal = fc.showSaveDialog(null);
		if(retriVal == JFileChooser.APPROVE_OPTION) {
			try {
				writer = new BufferedWriter(new FileWriter(fc.getSelectedFile()));
				writer.close();
				JOptionPane.showMessageDialog(null, "File has been saved", "File saved", JOptionPane.INFORMATION_MESSAGE);
				//input file info code
				//set file extension
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(retriVal == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "File save has been canceled");
		}
	}
}
