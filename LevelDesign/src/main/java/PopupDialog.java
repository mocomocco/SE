import java.awt.*;
import javax.swing.*;

public class PopupDialog extends JDialog
{
	JDialog alert;
	JPanel p1, p2, p3;
	JLabel nameLabel, widthLabel, heightLabel;
	JTextField nameText, widthText, heightText;
	//JComboBox<String> list;
	JButton ok, cancel;
	static int wid, hei;

	public PopupDialog(int count)
	{
		//String size[] = {"10x10", "20x20", "30x30"};
		alert = new JDialog(this, "New", true);

		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		
		nameLabel = new JLabel("Name:", JLabel.CENTER);
		nameText = new JTextField("Untitled", 13);
		widthLabel = new JLabel("Width:", JLabel.CENTER);
		heightLabel = new JLabel("Height:", JLabel.CENTER);
		widthText = new	JTextField("", 4);
		heightText = new JTextField("", 4);
		//list = new JComboBox<>(size);
		ok = new JButton("OK");
		cancel = new JButton("Cancel");

		alert.setLayout(new FlowLayout());
		alert.setSize(200, 230); 
		alert.setLocation(500, 250); 
		pack();
		
		p1.setLayout(new GridLayout(2,1,0,10));
		p2.setLayout(new GridLayout(2,2,25,10));
		p3.setLayout(new FlowLayout(FlowLayout.CENTER, 10,20));
		p1.add(nameLabel);	p1.add(nameText);
		p2.add(widthLabel);	p2.add(heightLabel);
		p2.add(widthText);	p2.add(heightText);
		//p2.add(list);
		p3.add(cancel);	p3.add(ok);
		
		alert.add(p1);
		alert.add(p2);
		alert.add(p3);

		ok.addActionListener(e -> {
			String name = nameText.getText();
			wid = Integer.parseInt(widthText.getText());
			hei = Integer.parseInt(heightText.getText());
			//String sizeName = list.getSelectedItem().toString();
			
			System.out.println("name: " + name+ "size: " + wid + "x" + hei + "\n" + "count" + count);
			Level newlevel= new Level(name,wid,hei);
			Display d = new Display("Level Design", newlevel,1);
			alert.setVisible(false);
		});
		
		cancel.addActionListener(e -> {
			alert.setVisible(false);
		});
		
		if (count == 1)
		{
			alert.setVisible(true);
		}
		this.setResizable(false);
	}
}