import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Menus extends MenuBar implements ActionListener
{
	MenuItem fileNew, fileOpen, fileExit, fileSave, fileSaveAs, fileOpenImg, fileHelp;
	JDialog jn, js;
	Level thislevel;

	public Menus(Level level)
	{
		thislevel=level;
		Menu file = new Menu("File");
		fileNew = new MenuItem("New");
		fileNew.addActionListener(this);
		fileOpen = new MenuItem("Open");
		fileOpen.addActionListener(this);
		fileOpenImg = new MenuItem("Open image");
		fileOpenImg.addActionListener(this);
		fileExit = new MenuItem("Exit");
		fileExit.addActionListener(this);
		fileSave = new MenuItem("Save");
		fileSave.addActionListener(this);
		fileSaveAs = new MenuItem("Save As");
		file.add(fileNew); file.add(fileOpen); file.add(fileOpenImg); file.addSeparator();
		file.add(fileSave); file.add(fileSaveAs); file.addSeparator(); file.add(fileExit);
		Menu edit = new Menu("Edit");
		Menu help = new Menu("Help");
		fileHelp = new MenuItem("Help");
		fileHelp.addActionListener(this);
		help.add(fileHelp);

		add(file);
		add(edit);
		add(help);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == fileExit) {
			System.exit(0);
		} else if(e.getSource() == fileOpen){
			new OpFileDialogs();
		} else if(e.getSource() == fileNew){
			int count = 1;
			jn = new PopupDialog(count);
		} else if(e.getSource() == fileOpenImg) {
			//some codes
		} else if(e.getSource() == fileSave) {
			js = new SvPopup(thislevel);
		} 
	}

}
