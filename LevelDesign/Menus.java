import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menus extends MenuBar implements ActionListener
{
	FileDialog fd;
	MenuItem fileNew, fileOpen, fileExit, fileSave, fileSaveAs;

	public Menus()
	{
		Menu file = new Menu("File");
		fileNew = new MenuItem("New");
		fileOpen = new MenuItem("Open");
		fileOpen.addActionListener(this);
		fileExit = new MenuItem("Exit");
		fileSave = new MenuItem("Save");
		fileSaveAs = new MenuItem("Save As");
		file.add(fileNew); file.add(fileOpen); file.add(fileExit);
		file.add(fileSave); file.add(fileSaveAs);
		Menu edit = new Menu("Edit");
		Menu help = new Menu("Help");

		add(file);
		add(edit);
		add(help);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == fileOpen){
			new FileDialogs();
		}
	}

}