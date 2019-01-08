import java.awt.*;
import javax.swing.*;

class PopupDialog extends JDialog
{
	JDialog alert;
	JPanel p1, p2, p3;
	JLabel nameLabel, setLabel;
	JTextField nameText;
	JComboBox<String> list;
	JButton ok, cancel;

	public PopupDialog()
	{
		String size[] = {"10x10", "20x20", "30x30"};
		alert = new JDialog(this, "New", true);
		
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		
		nameLabel = new JLabel("Name:", JLabel.CENTER);
		nameText = new JTextField("Untitled", 13);
		setLabel = new JLabel("Presets:", JLabel.CENTER);
		list = new JComboBox<>(size);
		ok = new JButton("OK");
		cancel = new JButton("Cancel");

		alert.setLayout(new FlowLayout());
		alert.setSize(200, 230); 
		alert.setLocation(500, 250); 
		pack();
		
		p1.setLayout(new GridLayout(2,1));
		p2.setLayout(new GridLayout(2,1));
		p3.setLayout(new FlowLayout(FlowLayout.CENTER, 10,20));
		p1.add(nameLabel);
		p1.add(nameText);
		p2.add(setLabel);
		p2.add(list);
		p3.add(cancel);
		p3.add(ok);
		
		alert.add(p1);
		alert.add(p2);
		alert.add(p3);

		ok.addActionListener(e -> {
			alert.setVisible(false); //later change to create level
		});
		
		cancel.addActionListener(e -> {
			alert.setVisible(false);
		});

		alert.setVisible(true);
		this.setResizable(false);
	}
}